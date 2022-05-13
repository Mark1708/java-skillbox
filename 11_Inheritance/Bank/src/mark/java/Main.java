package mark.java;


import mark.java.accounts.bank.CardAccount;
import mark.java.accounts.client.Individual;
import mark.java.accounts.client.IndividualEntrepreneur;
import mark.java.accounts.client.LegaEntity;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static boolean checkFormat(String line){
        Pattern pattern = Pattern.compile("^[0-9]+.{0,1}[0-9]*$");
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()){
            return true;
        }
        return false;
    }
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        IndividualEntrepreneur individualEntrepreneur = new IndividualEntrepreneur(new CardAccount());
        Individual individual = new Individual(new CardAccount());
        LegaEntity legaEntity = new LegaEntity(new CardAccount());


        System.out.println("Введите сумму для первого зачисления");
        String cash = scanner.nextLine();
        if (checkFormat(cash)){
            individualEntrepreneur.depositCash(Double.parseDouble(cash));
            individual.depositCash(Double.parseDouble(cash));
            legaEntity.depositCash(Double.parseDouble(cash));
            System.out.println("Саксес, деньги были зачислены" +
                    individualEntrepreneur.getInfo() + "\n" +
                    individual.getInfo() + "\n" +
                    legaEntity.getInfo()
            );
        } else {
            System.out.println("Дела плохи, вы не можете отлечить буквы от числа или переводите несуществующие деньги!");
        }

        System.out.println("Введите сумму для снятия");
        cash = scanner.nextLine();
        if (checkFormat(cash)){
            individualEntrepreneur.withdrawCash(Double.parseDouble(cash));
            individual.withdrawCash(Double.parseDouble(cash));
            legaEntity.withdrawCash(Double.parseDouble(cash));
            System.out.println("Саксес, деньги были сняты" +
                    individualEntrepreneur.getInfo() + "\n" +
                    individual.getInfo() + "\n" +
                    legaEntity.getInfo()
            );
        } else {
            System.out.println("Дела плохи, вы не можете отлечить буквы от числа или переводите несуществующие деньги!");
        }
    }
}
