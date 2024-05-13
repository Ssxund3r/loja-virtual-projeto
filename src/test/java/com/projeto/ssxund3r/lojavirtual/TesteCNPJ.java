package com.projeto.ssxund3r.lojavirtual;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import com.projeto.ssxund3r.lojavirtual.util.ValidaCNPJ;

@Profile("test")
@SpringBootTest(classes = LojaVirtualProjetoApplication.class)
public class TesteCNPJ {
	public static void main(String[] args) {
		String[] testCNPJs = { "00000000000000", "11111111111111", "22222222222222", "33333333333333", "44444444444444",
				"55555555555555", "66666666666666", "77777777777777", "88888888888888", "99999999999999",
				"12345678901234", "1234567890123512" };

		for (String cnpj : testCNPJs) {
			// Verifica se o CNPJ tem mais de 14 dígitos
			if (cnpj.length() > 14) {
				System.out.println(
						"Erro: CNPJ inválido - deve ter no máximo 14 dígitos. " + ValidaCNPJ.imprimeCNPJ(cnpj));
				continue;
			}

			String formattedCnpj = ValidaCNPJ.imprimeCNPJ(cnpj);
			System.out.println("CNPJ: " + formattedCnpj);
			System.out.println();
		}
	}
}
