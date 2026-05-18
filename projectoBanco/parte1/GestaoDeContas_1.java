package projectoIndividual.banco.parte1;

import java.util.Scanner;

public class GestaoDeContas_1 {
	static Scanner sc = new Scanner(System.in);	
	
	public static ContaBancaria criarConta() {
		
		// Numero da conta
		int numeroConta;
		System.out.print("Digite o número da conta (deve ter 5 dígitos): ");
		String input = sc.nextLine();

		while (true) {				
			try {
				numeroConta = Integer.parseInt(input);
				if (numeroConta >= 10000 && numeroConta <= 99999) {
		            break;
		        }
		        System.out.print("Número da conta deve ter 5 dígitos. Tente novamente: ");
		        input = sc.nextLine();
			}
			catch (NumberFormatException e) {
		        System.out.print("Só sao permitidos números! Digite novamente: "); 
		        input = sc.nextLine();
		    }	
		}
		
		// Titular
		System.out.print("Digite o titular: ");
		String titular = sc.nextLine();
		while(true) {
			if (titular != null && !titular.isEmpty() && titular.length() <= 40) {
				break;
			}
			System.out.print("Nome com caracteres inválidos. Digite novamente: ");
			titular = sc.nextLine();
		}
		
		// Tipo de conta
		int tipoConta;
		
		do {
			System.out.println("1 - Conta Ordem");
			System.out.println("2 - Conta Prazo");
			System.out.print("Escolha: ");
			tipoConta = sc.nextInt();
			sc.nextLine();
			System.out.println("------------------");

			if (tipoConta != 1 && tipoConta != 2) {
				System.out.println("Opção inválida!");
			}

		} while (tipoConta != 1 && tipoConta != 2);
		
		if (tipoConta == 1) {
			int regimeOpcao;

			do {
				System.out.println("=== REGIME ===");
				System.out.println("1 - Individual");
				System.out.println("2 - Solidária");
				System.out.println("3 - Conjunta");
				System.out.println("4 - Mista");
				System.out.print("Escolha: ");
				regimeOpcao = sc.nextInt();
				sc.nextLine();
				System.out.println("------------------");
			} while (regimeOpcao < 1 || regimeOpcao > 4);
			
			RegimeTitularidade regime = null;
			
			switch (regimeOpcao) {
				case 1:
					regime = RegimeTitularidade.INDIVIDUAL;
					break;
				case 2:
					regime = RegimeTitularidade.SOLIDARIA;
					break;
				case 3: 
					regime = RegimeTitularidade.CONJUNTA;
					break;
				case 4:
					regime = RegimeTitularidade.MISTA;
					break;
			}
					
				return new ContaOrdem(numeroConta, titular, regime);
			} else {
				return new ContaPrazo(numeroConta, titular);
			}
		
		}
	}

