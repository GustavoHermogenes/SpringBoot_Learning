package com.example.Bradesco.service;

import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Bradesco.repository.CartaoRepository;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository cartaoRepository;

    private Random random = new Random();

    public String gerarNumeroCartaoUnico() {
        String numeroCartao;
        do {
            numeroCartao = gerarNumeroCartao();
        } while (cartaoRepository.existsByNumeroCartao(numeroCartao)); // Verifica se já existe no banco
        return numeroCartao;
    }

    private String gerarNumeroCartao() {
        StringBuilder sb = new StringBuilder("4024"); // Exemplo BIN Bradesco/VISA
        // Gerar 11 números aleatórios
        for (int i = 0; i < 11; i++) {
            sb.append(random.nextInt(10)); // 0-9
        }
        // Calcular dígito verificador (Luhn Algorithm)
        String semDigito = sb.toString();
        int digito = calcularDigitoLuhn(semDigito);
        sb.append(digito);
        return sb.toString();
    }

    private int calcularDigitoLuhn(String numero) {
        int soma = 0;
        boolean alterna = true;

        for (int i = numero.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(numero.substring(i, i + 1));
            if (alterna) {
                n *= 2;
                if (n > 9) n -= 9;
            }
            soma += n;
            alterna = !alterna;
        }
        return (10 - (soma % 10)) % 10;
    }

    
    public String gerarCVV() {
        Random random = new Random();
        int cvv = 100 + random.nextInt(900); // Gera número entre 100 e 999
        return String.valueOf(cvv);
    }

}
