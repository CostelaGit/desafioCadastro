package dominio;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class PET implements Serializable{

    private String NomeESobrenome;
    private TIPO tipoAnimal;
    private SEXO sexo;
    private String ENDERECO;
    private double Idade;
    private double Peso;
    private String Raça;

    public PET() throws IOException {
    }

    public void PET() throws IOException {
        int index = 0;
        Scanner in = new Scanner(System.in);

        try {
            BufferedReader br = new BufferedReader(new FileReader("formulario.txt"));

            String linha;
            while ((linha = br.readLine()) != null) {
                ++index;
                System.out.println(linha);

                if (index == 4){
                    ENDERECO end = new ENDERECO();

                    System.out.println("4.1 - Digite o Número da Casa");
                    end.setNúmeroDaCasa(Integer.parseInt(in.nextLine()));
                    System.out.println("4.2 - Digite a Cidade");
                    end.setCidade(in.nextLine());
                    System.out.println("4.3 - Digite a Rua");
                    end.setRua(in.nextLine());

                    setENDERECO(end.toString());
                    continue;
                }

                String resposta = in.nextLine();

                if (index == 1){
                    if(resposta.isBlank()){
                        throw new IOException("É Obrigatorio o nome e sobrenome");
                    }
                    if (resposta.matches(".*[^a-zA-ZáàâãéêíóôõúçÁÀÂÃÉÊÍÓÔÕÚÇ ].*")){
                            throw new IOException("Nome não pode conter números ou caracteres especiais");
                    }
                    setNomeESobrenome(resposta);
                }

                if (index == 2){
                    setTipoAnimal(TIPO.valueOf(resposta.toUpperCase()));
                }

                if (index == 3){
                    setSexo(SEXO.valueOf(resposta.toUpperCase()));
                }

                if (index == 5){
                    resposta = resposta.replace(",",".");
                    setIdade(Double.parseDouble(resposta));
                }

                if (index == 6){
                    setPeso(Double.parseDouble(resposta));
                }

                if (index == 7){
                    setRaça(resposta);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public String getNomeESobrenome() {
        return NomeESobrenome;
    }

    public void setNomeESobrenome(String nomeESobrenome) {
        NomeESobrenome = nomeESobrenome;
    }

    public SEXO getSexo() {
        return sexo;
    }

    public void setSexo(SEXO sexo) {
        this.sexo = sexo;
    }

    public TIPO getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(TIPO tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }

    public String getENDERECO() {
        return ENDERECO;
    }

    public void setENDERECO(String ENDERECO) {
        this.ENDERECO = ENDERECO;
    }

    public double getIdade() {
        return Idade;
    }

    public void setIdade(double idade) {
        Idade = idade;
    }

    public double getPeso() {
        return Peso;
    }

    public void setPeso(double peso) {
        Peso = peso;
    }

    public String getRaça() {
        return Raça;
    }

    public void setRaça(String raça) {
        Raça = raça;
    }

    @Override
    public String toString() {
        return "PET{" +
                "NomeESobrenome='" + getNomeESobrenome() + '\'' +
                ", tipoAnimal=" + getTipoAnimal() +
                ", sexo=" + getSexo() +
                ", ENDERECO=" + getENDERECO() +
                ", Idade=" + getIdade() +
                ", Peso=" + getPeso() +
                ", Raça='" + getRaça() + '\'' +
                '}';
    }
    public void serializar() {
        Path path = Paths.get("codigo/petSerializado.txt");

        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path))) {
            oos.writeObject(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}




