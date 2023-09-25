import java.util.*;

// Задание

// Реализуйте структуру телефонной книги с помощью HashMap.
// Программа также должна учитывать, что во входной структуре будут повторяющиеся имена
// с разными телефонами, их необходимо считать, как одного человека с разными телефонами.



public class PhoneBook {

    static void sortedPrint(Map<String, HashSet<Object>> map) {

        Set<String> keySet = map.keySet();

        int maxCount = 0;
        int minCount = 1_000_000;

        for (var item : map.entrySet()) {
            if (maxCount < item.getValue().size())
                maxCount = item.getValue().size();
            if (minCount > item.getValue().size())
                minCount = item.getValue().size();

        }

        Stack<String> st = new Stack<>();
        int num = minCount;
        while (num <= maxCount) {
            for (var item : map.entrySet()) {
                if (item.getValue().size() == num) {
                    st.push(item.getKey());
                }
            }
            num += 1;
        }
        String name;
        for (int i = 0; i < keySet.size(); i++) {
            name = st.pop();
            for (var item : map.entrySet()) {
                if (Objects.equals(name, item.getKey())) {
                    System.out.printf("%8s: ", item.getKey());
                    System.out.println(item.getValue());
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Map<String, HashSet<Object>> abon = new HashMap<>() {
            {
                put("Messi", new HashSet<>() {
                    {
                        add(795034554);
                        add(234576);
                        add(791361423);

                    }
                });
                put("Ovechkin", new HashSet<>() {
                    {
                        add(555464);
                    }
                });
                put("Lebron", new HashSet<>() {
                    {
                        add(234774);
                        add(238765);

                    }
                });
                put("Hamilton", new HashSet<>() {
                    {
                        add(654722);
                        add(141844);
                        add(224657);
                        add(951654);
                    }
                });
            }
        };
        System.out.println();
        System.out.println("Исходные данные: ");
        sortedPrint(abon);

        Scanner scan = new Scanner(System.in, "cp866");
        boolean getOut = false;
        String st;
        while (!getOut) {
            System.out.println("""
                    Введите номер действия (1 - добавить абонента
                     2 - Поиск номера по имени
                     9 - выйти из программы
                     3 - Удалить абонента\s""");
            st = scan.nextLine();
            String name;
            String phString;
            switch (st) {
                case "1": {
                    System.out.println("Введите фамилию абонента:");
                    name = scan.nextLine();
                    if (abon.containsKey(name)) {
                        System.out.println("Такая фамилия уже есть. Больше фантазии!");
                        System.out.println();
                    } else {
                        System.out.println("Введите номера телефонов через запятую: ");
                        phString = scan.nextLine();
                        String[] arr = phString.split(",");
                        HashSet<Object> arrInt = new HashSet<>();
                        for (String item: arr) {
                            arrInt.add(Integer.parseInt(item.trim())) ;
                        }
                        abon.put(name, arrInt);
                        System.out.println();
                        sortedPrint(abon);
                    }
                    break;
                }
                case "9": {
                    getOut = true;
                    System.out.println();
                    System.out.println("До свидания!");
                    System.out.println();
                    break;
                }
                case "2": {
                    System.out.println("Введите фамилию абонента:");
                    name = scan.nextLine();
                    if(abon.containsKey(name)){
                        System.out.println(abon.get(name));
                    }
                    else {
                        System.out.println("Такого нет");
                    }

                }
                case "3": {
                    System.out.println("Введите фамилию абонента:");
                    name = scan.nextLine();
                    if (abon.containsKey(name)){
                        abon.remove(name);
                        System.out.println();
                        sortedPrint(abon);
                    }
                }
            }
        }
    }
}