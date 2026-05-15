package application;

import model.entities.Motorista;
import model.entities.StatusEntrega;
import model.exceptions.KilometragemInvalidaException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class RotaXLog {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in); // scanner padrão

        Map<Motorista, Double> controleKM = new HashMap<>(); // map do tipo motorista para controle de KM
        Set<String> placasUnicas = new HashSet<>(); // set do tipo String para evitar placas duplicadas
        List<Motorista> auditoriaFalhas = new ArrayList<>(); // list do tipo motorista para listar somente as FALHAS

        System.out.print("Digite o seu caminho csv: "); // aqui eu peço para o usuario digitar o caminho
        String caminho = sc.nextLine(); // leio o caminho
        // instancio o BufferedReader passando o caminho para o FileReader(caminho)
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {

            //faço essa variavel cabecalho para caso tenha cabecalho ele "ignore"
            String cabecalho = br.readLine();
            // crio uma variavel linha que vai ser lida com br.readLine();
            String linha = br.readLine();

            //aqui enquanto a linha não for nula, ou seja enquanto houver linha faça:
            while (linha != null) {

                //aqui eu crio uma variavel campos que é um vetor, passando split "," como separador
                String[] campos = linha.split(",");
                // pego na posição [0] pois id é o primeiro
                Integer id            = Integer.parseInt(campos[0]);
                // pego na posição [1] pois nome é o segundo
                String  nome          = campos[1];
                // pego na posição [2] pois placa é o terceiro
                String  placa         = campos[2];
                // pego na posição [3] pois km é o quarto
                Double  km            = Double.parseDouble(campos[3]);
                // pego na posição [4] pois status é o quinto
                StatusEntrega status = StatusEntrega.valueOf(campos[4]);

               //aqui faço uma condição caso se caso o km seja negativo lançe uma exceção do tipo KilometragemInvalidaException
                if (km < 0) {
                    throw new KilometragemInvalidaException(
                            "KM negativo para: " + nome
                    );
                }
                //aqui estancio um motorista do tipo Motorista, passando os "campos" que eu separei
                Motorista motorista = new Motorista(id, nome, placa, km);

               //aqui usando o set, eu adiciono as placas, como set não permite repetição, as placas vão ser unicas
                placasUnicas.add(placa);

                //aqui se controleKM.containsKey(motorista)
                if (controleKM.containsKey(motorista)) {
                    // adicione ou sobreescreva controleKM
                    controleKM.put(motorista, controleKM.get(motorista) + km);
                } else {
                    //caso contrario apenas adicione
                    controleKM.put(motorista, km);
                }
                // se status == FALHA
                if (status == StatusEntrega.FALHA) {
                    //adiciono as falhas com a list auditoriaFalhas.
                    auditoriaFalhas.add(motorista);
                }
                //aqui leio a ultima linha
                linha = br.readLine();
            }
            //aqui printo o relátorio
            System.out.println("======RELATORIO ROTAX LOGÍSTICA======");
            System.out.println("Total de veículos únicos: " + placasUnicas.size()); // total de veiculos
            System.out.println("Total de falhas: " + auditoriaFalhas.size()); // total de falhas
            System.out.println("\n--- Consolidação de KM por Motorista ---"); // printo "tudo" da lista

             // printo tudo com o controleKM.keySet()) no foreach
            for (Motorista m : controleKM.keySet()) {
                System.out.println(
                        "ID: "      + m.getId()   + // pego pego o id com o m.getId()
                                " | Nome: " + m.getNome() + // pego pego o nome com o m.getNome()
                                " | Total: " + controleKM.get(m) + // pego pego o km com o controleKM.get(m)
                                " KM"
                );
            }
             // tratamento de possiveis solucoes
        } catch (IOException e) {
            System.out.println("ERRO: " + e.getMessage());

        } catch (KilometragemInvalidaException e) {
            System.out.println("ERRO DE AUDITORIA: " + e.getMessage());

        } finally {
            sc.close();
        }
    }
}