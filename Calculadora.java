
import java.util.Scanner;

public class Calculadora {

    public static void main(String[] args) {
        double COTACAODOLAR = 5.67;
        double EMISSAOCARBONOGASOLINA = 2.2755;
        double EMISSAOCARBONOETANOL = (EMISSAOCARBONOGASOLINA * 0.73);
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

        System.out.printf("O seu custo mensal com o abastecimendo por %s é de %.2f", combustivel, custoFinal);
        System.out.printf("O custo real por litro de %s é de %.2f", combustivel, custoFinalPorLitro);

        entrada.close();
    }

}