public class Pet {
    private String nome;
    private SEXO sexo;
    private TIPO tipo;
    private endereco endereco;
    private float idade;
    private float peso;
    private String raca;

    public Pet(String nome, SEXO sexo, TIPO tipo, endereco endereco, float idade, float peso, String raca) {
        this.nome = nome;
        this.sexo = sexo;
        this.tipo = tipo;
        this.endereco = endereco;
        this.idade = idade;
        this.peso = peso;
        this.raca = raca;

    }
    @Override
    public String toString() {
        return "Pet{" +
                "nome='" + nome + '\'' +
                ", sexo=" + sexo +
                ", tipo=" + tipo +
                ", endereco=" + endereco +
                ", idade=" + idade +
                ", peso=" + peso +
                ", raca='" + raca + '\'' +
                '}';
    }

  }



