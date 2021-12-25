
import java.util.Scanner;

public class Calculadora {

    public static void main(String[] args) {
        double COTACAODOLAR = 5.67;
        double EMISSAOCARBONOGASOLINA = 2.264; // MACEDO et al. (2004)
        double EMISSAOCARBONOETANOL = 1.722; // MACEDO et al. (2004)
        double PRECOCARBONOTON = 3.31;
        // Fórmula de cálculo de emissão de carbono para Gasolina -> 1 litro de gasolina
        // = 1 x 0,82 x 0,75 x 3,7 = total de kg CO2 emitido por litro.

        Scanner entrada = new Scanner(System.in);

        System.out.println("Informe o consumo em Litro/Km do seu carro: ");
        float consumoLitroporKm = entrada.nextFloat();

        System.out.println("Informe a kilometragem rodada mensalmente: ");
        int kilometragemMensal = entrada.nextInt();

        System.out.println("Qual o combustível utilizado (G - gasolina ou E - etanol): ");
        var combustivel = entrada.next();

        // *********** PROCESSAMENTO ************//

        double tipoCombustivel;
        if (combustivel.equalsIgnoreCase("G")) {
            System.out.println("Qual o valor da gasolina na sua cidade?");
            double precoGasolina = entrada.nextDouble();
            tipoCombustivel = precoGasolina;
        } else {
            System.out.println("Qual o valor do etanol na sua cidade?");
            double precoEtanol = entrada.nextDouble();
            tipoCombustivel = precoEtanol;
        }

        // Consumo Mensal em Litros
        float consumoMensalLitro = kilometragemMensal / consumoLitroporKm;

        // Gasto Mensal com o combustível escolhido
        double gastoMensal = consumoMensalLitro * tipoCombustivel;

        // Carbono Emitido
        double carbonoEmitido = 0;

        if (combustivel.equalsIgnoreCase("G")) {
            carbonoEmitido = consumoMensalLitro * EMISSAOCARBONOGASOLINA;
            // Não é a metodologia “poço-à-roda”
        } else {
            carbonoEmitido = consumoMensalLitro * EMISSAOCARBONOETANOL;
            // Fórmula de cálculo de emissão de carbono para Etanol -> 73% da emissão da
            // gasolina
        }

        // Custo do carbono no mercado
        double custoCarbonoReal = (PRECOCARBONOTON / 1000) * COTACAODOLAR;

        // Custo da emissão
        double custoDaEmissao = carbonoEmitido * custoCarbonoReal;

        // Custos reais
        double custoFinal = gastoMensal + custoDaEmissao;

        // Custo por litro
        double custoFinalPorLitro = 0;
        if (combustivel.equalsIgnoreCase("G")) {
            custoFinalPorLitro = tipoCombustivel + (EMISSAOCARBONOGASOLINA * custoCarbonoReal);
        } else {
            custoFinalPorLitro = tipoCombustivel + (EMISSAOCARBONOETANOL * custoCarbonoReal);
        }

        // *********** SAÍDAS ************//

        System.out.printf("O seu custo mensal com o abastecimendo por %s é de R$%.2f\n", combustivel, custoFinal);
        System.out.printf("O custo real por litro de %s é de R$%.2f", combustivel, custoFinalPorLitro);


        // Testar para o outro combustível
        System.out.println("Gostaria de saber os custos para abastecimento com o outro combustível?");
        System.out.println("S - Sim / N - Não");
        var outroCombustivel = entrada.nextLine();

        if (outroCombustivel.equalsIgnoreCase("S")) {
            System.out.println("Qual o valor do outro combustível?");
            double precoOutro = entrada.nextDouble();

            double gastoMensalOutro = consumoMensalLitro * precoOutro;

            if (combustivel.equalsIgnoreCase("G")) {
                carbonoEmitido = consumoMensalLitro * EMISSAOCARBONOETANOL;
            } else {
                carbonoEmitido = consumoMensalLitro * EMISSAOCARBONOGASOLINA;
            }

            // Custo da emissão
            double custoDaEmissaoOutro = carbonoEmitido * custoCarbonoReal;

            // Custos reais
            double custoFinalOutro = gastoMensalOutro + custoDaEmissaoOutro;

            // Custo por litro Outro
            double custoFinalPorLitroOutro = 0;
            if (combustivel.equalsIgnoreCase("G")) {
                custoFinalPorLitroOutro = precoOutro + (EMISSAOCARBONOETANOL * custoCarbonoReal);
            } else {
                custoFinalPorLitroOutro = precoOutro + (EMISSAOCARBONOGASOLINA * custoCarbonoReal);
            }

            // *********** SAÍDAS ************//
            
            System.out.printf("O custo mensal com o abastecimendo por este combustível seria de R$%.2f\n", combustivel,
                    custoFinalOutro);
            System.out.printf("E custo real por litro seria de R$%.2f", combustivel, custoFinalPorLitroOutro);
        } else {
            System.out.println("Calculadora Encerrada");
        }

        entrada.close();
    }

}