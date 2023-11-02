package 북스터디.five_lines_of_code._5주차.compiler;

import java.util.ArrayList;
import java.util.List;

public class CompilerTest {
    enum Color {
        RED, GREEN, BLUE,
    }

    String returnColorCode(Color color) {
        if (color == Color.RED) {
            return "#ff0000";
        }

        if (color == Color.GREEN) {
            return "#00ff00";
        }

        if (color == Color.BLUE) {
            return "#0000ff";
        }

        throw new RuntimeException();
    }

    String returnColorCode2(Color color) {
        return switch (color) {
            case RED -> "#ff0000";
            case GREEN -> "#00ff00";
//            case BLUE -> "#0000ff";
            default -> throw new RuntimeException();
        };
    }

    static class Member {
        private final String name;

        public Member(String name) {
            this.name = name;
        }
    }

    private List<Member> members = List.of(new Member("John"));

    public String findMember() {
        String result = "";

        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).name.equals("John")) {
                result = members.get(i).name;
            }
        }

        return result;
    }

    static void print(String name, String nickName, String email) {
        System.out.println("name = " + name);
        System.out.println("nickName = " + nickName);
        System.out.println("email = " + email);
    }

    public static void main(String[] args) {
//        print(1, 2, 3);
        print("name", "nickName", "email");
        print("email", "name", "nickName");
    }
}
