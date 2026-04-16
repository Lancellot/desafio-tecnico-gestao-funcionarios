package com.industria;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final NumberFormat FORMATO_NUMERO = NumberFormat.getInstance(new Locale("pt", "BR"));

    public static void main(String[] args) {

        List<Funcionario> funcionarios = new ArrayList<>();

        funcionarios.add(new Funcionario("Maria",    LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"),  "Operador"));
        funcionarios.add(new Funcionario("João",     LocalDate.of(1990,  5, 12), new BigDecimal("2284.38"),  "Operador"));
        funcionarios.add(new Funcionario("Caio",     LocalDate.of(1961,  5,  2), new BigDecimal("9836.14"),  "Coordenador"));
        funcionarios.add(new Funcionario("Miguel",   LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice",    LocalDate.of(1995, 1,   5), new BigDecimal("2234.68"),  "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor",   LocalDate.of(1999,  11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur",   LocalDate.of(1993,  3, 31), new BigDecimal("4071.84"),  "Contador"));
        funcionarios.add(new Funcionario("Laura",    LocalDate.of(1994,  7,  8), new BigDecimal("3017.45"),  "Gerente"));
        funcionarios.add(new Funcionario("Heloísa",  LocalDate.of(2003,  5, 24), new BigDecimal("1606.85"),  "Eletricista"));
        funcionarios.add(new Funcionario("Helena",   LocalDate.of(1996,  9,  2), new BigDecimal("2799.93"),  "Gerente"));

        funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase("João"));
        System.out.println("✔ Funcionário João removido da lista.\n");

        System.out.println("══════════════════════════════════════════════════════");
        System.out.println("  3.3 – LISTA DE FUNCIONÁRIOS");
        System.out.println("══════════════════════════════════════════════════════");
        for (Funcionario f : funcionarios) {
            imprimirFuncionario(f);
        }

        for (Funcionario f : funcionarios) {
            BigDecimal novoSalario = f.getSalario().multiply(new BigDecimal("1.10"));
            f.setSalario(novoSalario);
        }
        System.out.println("\n✔ Salários atualizados com 10% de aumento.\n");

        Map<String, List<Funcionario>> porFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));


        System.out.println("══════════════════════════════════════════════════════");
        System.out.println("  3.6 – FUNCIONÁRIOS AGRUPADOS POR FUNÇÃO");
        System.out.println("══════════════════════════════════════════════════════");
        for (Map.Entry<String, List<Funcionario>> entry : porFuncao.entrySet()) {
            System.out.println("\n  Função: " + entry.getKey());
            System.out.println("  " + "─".repeat(48));
            for (Funcionario f : entry.getValue()) {
                imprimirFuncionario(f);
            }
        }

        System.out.println("\n══════════════════════════════════════════════════════");
        System.out.println("  3.8 – ANIVERSARIANTES EM OUTUBRO E DEZEMBRO");
        System.out.println("══════════════════════════════════════════════════════");
        List<Funcionario> aniversariantes = funcionarios.stream()
                .filter(f -> {
                    int mes = f.getDataNascimento().getMonthValue();
                    return mes == 10 || mes == 12;
                })
                .collect(Collectors.toList());

        if (aniversariantes.isEmpty()) {
            System.out.println("  Nenhum funcionário faz aniversário nesses meses.");
        } else {
            for (Funcionario f : aniversariantes) {
                imprimirFuncionario(f);
            }
        }

        System.out.println("\n══════════════════════════════════════════════════════");
        System.out.println("  3.9 – FUNCIONÁRIO COM MAIOR IDADE");
        System.out.println("══════════════════════════════════════════════════════");
        Funcionario maisVelho = funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElse(null);

        if (maisVelho != null) {
            int idade = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();
            System.out.printf("  Nome: %-15s | Idade: %d anos%n", maisVelho.getNome(), idade);
        }


        System.out.println("\n══════════════════════════════════════════════════════");
        System.out.println("  3.10 – FUNCIONÁRIOS EM ORDEM ALFABÉTICA");
        System.out.println("══════════════════════════════════════════════════════");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(f -> imprimirFuncionario(f));


        System.out.println("\n══════════════════════════════════════════════════════");
        System.out.println("  3.11 – TOTAL DOS SALÁRIOS");
        System.out.println("══════════════════════════════════════════════════════");
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("  Total: R$ " + formatarSalario(totalSalarios));

        System.out.println("\n══════════════════════════════════════════════════════");
        System.out.println("  3.12 – SALÁRIOS MÍNIMOS POR FUNCIONÁRIO (R$ 1.212,00)");
        System.out.println("══════════════════════════════════════════════════════");
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        for (Funcionario f : funcionarios) {
            BigDecimal qtdSalariosMinimos = f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.printf("  %-12s → %s salários mínimos%n",
                    f.getNome(), formatarSalario(qtdSalariosMinimos));
        }

        System.out.println("\n══════════════════════════════════════════════════════");
        System.out.println("  FIM DO RELATÓRIO");
        System.out.println("══════════════════════════════════════════════════════");
    }


    private static void imprimirFuncionario(Funcionario f) {
        System.out.printf("  Nome: %-12s | Nascimento: %s | Salário: R$ %15s | Função: %s%n",
                f.getNome(),
                f.getDataNascimento().format(FORMATO_DATA),
                formatarSalario(f.getSalario()),
                f.getFuncao());
    }


    private static String formatarSalario(BigDecimal valor) {
        FORMATO_NUMERO.setMinimumFractionDigits(2);
        FORMATO_NUMERO.setMaximumFractionDigits(2);
        return FORMATO_NUMERO.format(valor);
    }
}
