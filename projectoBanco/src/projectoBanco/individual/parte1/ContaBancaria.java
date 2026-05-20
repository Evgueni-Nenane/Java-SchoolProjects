package projectoBanco.individual.parte1;

public class ContaBancaria {
	protected int numeroConta;
	protected String titular;
	protected double saldo;

	public ContaBancaria() {}
	
	public ContaBancaria(int numeroConta, String titular) {
		if (numeroConta < 10000 || numeroConta > 99999) {
			throw new IllegalArgumentException("Número da conta deve conter no mínimo 5 números.");
		}
		this.numeroConta = numeroConta;
		
		if (titular == null || titular.isEmpty()) {
			throw new IllegalArgumentException("Titular nao pode ser vazio!");
		}
		this.titular = titular;
		this.saldo = 0;
	}

	public int getNumeroConta() {
		return numeroConta;
	}
	
	public String getTitular() {
		return titular;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public void depositar(double valor) {
		if (valor <= 0) {
			throw new IllegalArgumentException("Valor deve ser maior que 0!");
		}
		this.saldo += valor;
	}
	public double saldoReal() {
		return saldo;
	}

	@Override
	public String toString() {
		return "\n----------------"
				+ "\nConta Bancaria\nNúmero da Conta: " + numeroConta + "\nTitular: " + titular + "\nSaldo: " + saldo;
	}
}

