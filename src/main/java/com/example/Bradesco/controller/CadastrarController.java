package com.example.Bradesco.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Bradesco.model.Cartao;
import com.example.Bradesco.model.Cliente;
import com.example.Bradesco.model.Conta;
import com.example.Bradesco.repository.CartaoRepository;
import com.example.Bradesco.repository.ClienteRepository;
import com.example.Bradesco.repository.ContaRepository;
import com.example.Bradesco.service.CartaoService;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class CadastrarController {

    

    @Autowired
    private CartaoService cartaoService;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private final ContaRepository contaRepository;
    private final ClienteRepository clienteRepository;

    
    // construtor que recebe o repositório de contas como parâmetro
    public CadastrarController(ContaRepository contaRepository, 
                           ClienteRepository clienteRepository) {
        this.contaRepository = contaRepository;
        this.clienteRepository = clienteRepository;
    }


    @GetMapping("/Cadastro")
    public String Cadastrar() {
        return "Cadastro";
    }
    

       @GetMapping("/clienteNovo")
    public String novoCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("conta", new Conta());
        return "clienteNovo";
    }

    @PostMapping("/clienteNovo")
    public String cadastrarCliente(@ModelAttribute Cliente cliente,
                              @ModelAttribute Conta conta,
                              @ModelAttribute Cartao cartao,
                              Model model,
                              RedirectAttributes redirectAttributes) {

    // Salva o cliente
    cliente.setStatus(Cliente.status.ATIVO);
    clienteRepository.save(cliente);

    // Configura dados da conta
    conta.setAgencia(237);
    Integer maiorNumeroConta = contaRepository.maiorNumero();
    if (maiorNumeroConta == null) {
        maiorNumeroConta = 1000001;
    }
    conta.setNumero_conta(maiorNumeroConta + 1);
    conta.setData_abertura(java.time.LocalDate.now());
    conta.setSaldo(BigDecimal.ZERO);
    conta.setStatus(Conta.status.ATIVA);
    conta.setCliente(cliente);

    // Salva a conta
    contaRepository.save(conta);

    // Gera número do cartão usando o serviço (instância injetada)
    String numeroCartao = cartaoService.gerarNumeroCartaoUnico();
    cartao.setNumeroCartao(numeroCartao);

    String cvv = cartaoService.gerarCVV();
    cartao.setCodigoSeguranca(cvv);
    cartao.setTipoCartao(Cartao.TipoCartao.CREDITO_DEBITO);
    cartao.setLimite(BigDecimal.valueOf(300.00));
    cartao.setDataValidade(java.time.LocalDate.now().plusYears(5));
    cartao.setStatus(Cartao.Status.BLOQUEADO);
    cartao.setConta(conta);

    // Salva o cartão
    cartaoRepository.save(cartao);
    
        model.addAttribute("conta", conta);
        model.addAttribute("cliente", conta.getCliente());
        List<Cartao> cartoes = conta.getCartoes();
        if (cartoes == null) {
            cartoes = new ArrayList<>();
        }
        model.addAttribute("cartoes", cartoes); // Use a lista inicializada!
         // Use a lista inicializada!
        return "/HomePg";

}
    

    @PostMapping("/contaNova")
    public String cadastrarConta(@ModelAttribute Conta conta,
                                @RequestParam String cpf,
                                @RequestParam String senha,
                                @RequestParam String confirmeSenha,
                                 Model model,
                                 RedirectAttributes redirectAttributes){
        Cliente cliente = clienteRepository.findByCpf(cpf).orElse(null);

        if(cliente == null){
            model.addAttribute("erro", "Cliente não encontrado");
            return "contaNova";
        }

        if (!senha.equals(confirmeSenha)) {
            model.addAttribute("erro", "As senhas não conferem");
            return "contaNova";
        }

        conta.setCliente(cliente);
        
        conta.setAgencia(237);

        Integer maiorNumeroConta = contaRepository.maiorNumero();
        if (maiorNumeroConta == null) {
            maiorNumeroConta = 1000001;
        }
        conta.setNumero_conta(maiorNumeroConta + 1);

        conta.setData_abertura(java.time.LocalDate.now());

        conta.setSaldo(BigDecimal.ZERO);

        conta.setStatus(Conta.status.ATIVA);
        contaRepository.save(conta);

        redirectAttributes.addAttribute("agencia", conta.getAgencia());
        redirectAttributes.addAttribute("numero_conta", conta.getNumero_conta());

        
        return "redirect:/HomePg";
    }  
}
