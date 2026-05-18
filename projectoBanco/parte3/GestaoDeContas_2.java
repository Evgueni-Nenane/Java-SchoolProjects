package projectoIndividual.banco.parte3;

import java.util.Scanner;
import java.util.InputMismatchException;

public class GestaoDeContas_2 {
	
	static Scanner sc = new Scanner(System.in);
	static int capacidade;
	static ContaBancaria[] contas;
	
    public static void imprimirContas(ContaBancaria[] contas) {
        for (int i = 0; i < contas.length; i++) {
            System.out.println("Número: " + contas[i].getNumeroConta());
            System.out.println("Saldo: " + contas[i].getSaldo());
            System.out.printf("Saldo real: %.2f", contas[i].saldoReal());
            System.out.println("");
            System.out.println("------------------------");
            System.out.println("");
        }
    }
    
    public static void main(String[] args) {
		
		int opcao = -1;
		do {
			System.out.println("===============================");
			System.out.println("         MENU PRINCIPAL 	   ");
			System.out.println("===============================");
			System.out.println();
			System.out.println("--------------------------------");
			System.out.println("-- 1 - Criar Conta           --");
			System.out.println("-- 2 - Depositar             --");
			System.out.println("-- 3 - Depositar em todas    --");
			System.out.println("-- 4 - Listar Contas         --");
			System.out.println("-- 5 - Ver saldo real        --");
			System.out.println("-- 0 - Sair                  --");
			System.out.println("--------------------------------");
			System.out.print("Digite uma opçao: "); 
			
			try {
			opcao = sc.nextInt();
			
			switch (opcao) {
			case 1:
				System.out.print("Quantas contas quer criar? "); 
				capacidade = sc.nextInt();	
				
				contas = new ContaBancaria[capacidade];
				
				for (int i = 0; i < capacidade; i++) {
					contas[i] = GestaoDeContas_1.criarConta();
				}
				break;
			case 2:
				System.out.print("Digite o número da conta: ");
				int numeroPesquisa = sc.nextInt();
				sc.nextLine();
				
				boolean found = false;
				
				for (int i = 0; i < contas.length; i++) {
	
					if(numeroPesquisa == contas[i].getNumeroConta()) {
						System.out.print("Valor a depositar: ");
						
						double valor = sc.nextDouble(); sc.nextLine();
						contas[i].depositar(valor);
						
						found = true;
						break;
					}
				}
				if (!found) {
					System.out.println("Numero da conta nao encontrado!");
				}
				break;
			case 3:
				System.out.print("Valor a depositar: ");
				double valor = sc.nextDouble();
				
			    for (int i = 0; i < contas.length; i++) {
			        contas[i].depositar(valor);
			    }
			    System.out.println("Deposito de "+ valor + " efectuado em todas as contas.");
				break;
			case 4:
				imprimirContas(contas);				
				break;
			case 5:
				System.out.print("Ver de conta específica ou todas? (E/T)");
				char ver = sc.next().charAt(0);
				
				if (Character.toUpperCase(ver) == 'E') {
					System.out.print("Digite o número da conta: ");
					int num = sc.nextInt();
					sc.nextLine();
					
					for (int i = 0; i < contas.length; i++) {
						if (contas[i].getNumeroConta() == num) {
							System.out.print("O saldo real é: "+ contas[i].saldoReal());
							found = true;
							break;
						}
					}
				} else {
					  for (int i = 0; i < contas.length; i++) {
				            System.out.println(contas[i]);
				            System.out.println("Saldo real: " + contas[i].saldoReal());
				            System.out.println("------------------");
				        }
				}
				
				break;
			case 0:
				System.out.println("SAINDO DO MENU PRINCIPAL");
				break;
			default:
				System.err.println("OPÇAO INVÁLIDA");
				break;
			}
			} catch(InputMismatchException e) {
				System.err.println("Apenas números sao permitidos!");
				sc.nextLine();
			}
			
					
		}while (opcao != 0);
		sc.close();

	}

}
