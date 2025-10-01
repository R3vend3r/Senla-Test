package Task2;

import java.util.Scanner;

public class Crypto {

    enum Currency {
        RUB("Российский рубль", 1.0),
        USD("Доллар США", 92.5),
        EUR("Евро", 101.2),
        CNY("Китайский юань", 12.98),
        KZT("Казахстанский тенге", 0.195);

        private final String name;
        private double rateFromRUB;

        Currency(String name, double rateToRUB) {
            this.name = name;
            this.rateFromRUB = 1.0 / rateToRUB;
        }

        public String getName() {
            return name;
        }

        public double getRateFromRUB() {
            return rateFromRUB;
        }

        public void setRateFromRUB(double newRate) {
            this.rateFromRUB = newRate;
        }

        public static double convert(double amount, Currency from, Currency to) {
            if (from == to) {
                return amount;
            }

            double amountInRUB = amount / from.getRateFromRUB();
            return amountInRUB * to.getRateFromRUB();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== КОНВЕРТЕР ВАЛЮТ ===");
        System.out.println("Установка курсов валют к рублю\n");

        Currency[] currencies = Currency.values();

        System.out.println("Текущие курсы (сколько рублей стоит 1 единица валюты):");
        for (Currency currency : currencies) {
            if (currency != Currency.RUB) {
                double currentRate = 1.0 / currency.getRateFromRUB();
                System.out.printf("1 %s = %.2f RUB%n", currency.name(), currentRate);
            }
        }

        System.out.println("\nДоступные валюты:");
        for (int i = 0; i < currencies.length; i++) {
            System.out.printf("%d. %s (%s)%n", i + 1, currencies[i].name(), currencies[i].getName());
        }

        try {
            while (true) {
                System.out.print("\nВыберите исходную валюту (1-" + currencies.length + ", 0 для выхода): ");
                int sourceIndex = scanner.nextInt();

                if (sourceIndex == 0) break;
                if (sourceIndex < 1 || sourceIndex > currencies.length) {
                    System.out.println("Неверный выбор!");
                    continue;
                }

                Currency sourceCurrency = currencies[sourceIndex - 1];

                System.out.print("Введите сумму: ");
                double amount = scanner.nextDouble();

                System.out.print("Выберите целевую валюту (1-" + currencies.length + "): ");
                int targetIndex = scanner.nextInt();
                Currency targetCurrency = currencies[targetIndex - 1];

                double result = Currency.convert(amount, sourceCurrency, targetCurrency);

                System.out.println("\n" + "=".repeat(50));
                System.out.printf("Результат: %.2f %s = %.2f %s%n",
                        amount, sourceCurrency.name(), result, targetCurrency.name());

                double reverseRate = Currency.convert(1, targetCurrency, sourceCurrency);
                System.out.printf("Обратный курс: 1 %s = %.4f %s%n",
                        targetCurrency.name(), reverseRate, sourceCurrency.name());
                System.out.println("=".repeat(50));
            }

            System.out.println("Спасибо за использование конвертера!");

        } catch (Exception e) {
            System.out.println("Ошибка ввода!");
        } finally {
            scanner.close();
        }
    }
}