public class endereco {
    private int NumeroDaCasa;
    private String Cidade;
    private String Rua;

    public endereco(int NumeroDaCasa, String Cidade, String Rua){
        this.NumeroDaCasa = NumeroDaCasa;
        this.Cidade = Cidade;
        this.Rua = Rua;
    }

    @Override 
    public String toString() {
        return "endereco{" +
                "NumeroDaCasa=" + NumeroDaCasa +
                ", Cidade='" + Cidade + '\'' +
                ", Rua='" + Rua + '\'' +
                '}';
    }
}
