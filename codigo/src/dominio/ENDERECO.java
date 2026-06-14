package dominio;

public class ENDERECO {
    private int númeroDaCasa;
    private String Cidade;
    private String Rua;

    @Override
    public String toString() {
        return "Endereço{" +
                "númeroDaCasa=" + getNúmeroDaCasa() +
                ", Cidade='" + getCidade() + '\'' +
                ", Rua='" + getRua() + '\'' +
                '}';
    }

    public int getNúmeroDaCasa() {
        return númeroDaCasa;
    }

    public void setNúmeroDaCasa(int númeroDaCasa) {
        this.númeroDaCasa = númeroDaCasa;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String cidade) {
        Cidade = cidade;
    }

    public String getRua() {
        return Rua;
    }

    public void setRua(String rua) {
        Rua = rua;
    }
}

