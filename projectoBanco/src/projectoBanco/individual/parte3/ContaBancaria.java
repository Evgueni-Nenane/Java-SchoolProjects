package projectoBanco.individual.parte3;

public abstract class ContaBancaria implements SaldoReal {
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
		
		public abstract void depositar(double valor);

		@Override
		public String toString() {
			return "Conta Bancaria\nNúmero da Conta: " + numeroConta + "\nTitular: " + titular + "\nSaldo: " + saldo;
	}
}



