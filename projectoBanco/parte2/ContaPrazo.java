package projectoIndividual.banco.parte2;

public class ContaPrazo extends ContaBancaria {
	private double juros;
	
	public ContaPrazo(int numeroConta, String titular) {
		super(numeroConta, titular);
	}
	
	@Override
	public void depositar(double valor) {
		if (valor <= 0) {
			throw new IllegalArgumentException("O valor deve ser maior que 0!");
		}
		juros += valor * 0.03;
		this.saldo += (valor + (valor * 0.03));
	}
	@Override
	public double saldoReal() {
		return saldo - juros;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}

// 200 - (200/3)/100 == 199.33333
//