package projectoIndividual.banco.parte1;

public class ContaOrdem extends ContaBancaria {
	protected RegimeTitularidade regime;
	private double comissao;
	
	public ContaOrdem(int numeroConta, String titular, RegimeTitularidade regime) {
		super(numeroConta, titular);
		this.regime = regime;
	}
	
	@Override
	public void depositar(double valor) {
		if (valor <= 0) {
			throw new IllegalArgumentException("Valor deve ser maior que 0!");
		}
		comissao += valor * 0.01;
		saldo += (valor - (valor * 0.01));
	}
	@Override
	public double saldoReal() {
		return saldo + comissao;
	}
	@Override
	public String toString() {
		return super.toString() + "\nRegime de Titularidade: " + regime;
	}
}
// (200-(200*0.01) == 198
// 198 + (198*1) / 100 == 199.98