package com.projeto.ssxund3r.lojavirtual;

import com.projeto.ssxund3r.lojavirtual.util.ValidaCPF;

public class TesteCPF {
	public static void main(String[] args) {
		String[] testCPFs = { "00000000000", "11111111111", "22222222222", "33333333333", "44444444444", "55555555555",
				"66666666666", "77777777777", "88888888888", "99999999999", "12345678909", "123456789012", "951357456823218" };

		for (String cpf : testCPFs) {
			// Verifica se o CPF tem mais de 11 dígitos
			if (cpf.length() > 11) {
				System.out.println("Erro: CPF inválido - deve ter no máximo 11 dígitos. " + ValidaCPF.imprimeCPF(cpf));
				continue;
			}

			boolean resultado = ValidaCPF.isCPF(cpf);
			String formattedCpf = ValidaCPF.imprimeCPF(cpf);
			System.out.println("CPF: " + formattedCpf);
			System.out.println("CPF válido: " + resultado);
			System.out.println();
		}
	}
}
