package org.example;

//- 사용자에게 명령어를 반복해서 입력받는다
//- motivation의 속성(id, regDate, content, author)을 고려하여 클래스를 구현한다.
//- 등록, 목록, 상세보기, 삭제, 수정의 기능을 구현한다
//- 기능에 맞는 적절한 문구가 출력 되어야 한다
//- 모든 클래스는 각각의 파일로 만들어야 한다

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("== 명언 앱 실행 ==");
        Scanner sc = new Scanner(System.in);
        List<Motivation> motivations = new ArrayList<>();

        int lastId = 0;

        while (true) {
            System.out.print("명령어 ) ");
            String cmd = sc.nextLine().trim();

            if (cmd.equals("종료")) {
                System.out.println("== 명언 앱 종료 ==");
                break;
            }

            if (cmd.equals("등록")) {
                int id = lastId + 1;
                LocalDateTime now = LocalDateTime.now();
                String date = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
                System.out.print("명언 : ");
                String content = sc.nextLine().trim();
                System.out.print("작가 : ");
                String author = sc.nextLine().trim();

                Motivation motivation = new Motivation(id, date, content, author);
                motivations.add(motivation);

                System.out.println(id + "번 명언이 등록되었습니다.");
                lastId++;
            } else if (cmd.equals("목록")) {
                System.out.println("  번호  /  작가  /  명언  ");
                System.out.println("=".repeat(30));

                for (int i = motivations.size() - 1; i >= 0; i--) {
                    Motivation motivation = motivations.get(i);
                    System.out.printf(" %d / %s / %s \n", motivation.getId(), motivation.getAuthor(), motivation.getContent());
                }

            } else if (cmd.startsWith("수정?")) {
                int id = Integer.parseInt(cmd.split("=")[1]);

                Motivation foundId = null;
                for (Motivation motivation : motivations) {
                    if (id == motivation.getId()) {
                        foundId = motivation;
                        break;
                    }
                }

                if (foundId == null) {
                    System.out.println(id + "번 명언이 존재하지 않습니다.");
                }

                System.out.println("명언(기존) : " + foundId.getContent());
                System.out.println("작가(기존) : " + foundId.getAuthor());

                System.out.print("명언 : ");
                String content = sc.nextLine().trim();
                System.out.print("작가 : ");
                String author = sc.nextLine().trim();

                foundId.setContent(content);
                foundId.setAuthor(author);

                System.out.println(id + "번 명언이 수정되었습니다.");
            } else if (cmd.startsWith("상세보기?")) {
                int id = Integer.parseInt(cmd.split("=")[1]);

                Motivation foundId = null;

                for (Motivation motivation : motivations) {
                    if (id == motivation.getId()) {
                        foundId = motivation;
                        break;
                    }
                }

                if (foundId == null) {
                    System.out.println(id + "번 명언이 존재하지 않습니다.");
                }

                System.out.println("번호 : " + foundId.getId());
                System.out.println("날짜 : " + foundId.getRegDate());
                System.out.println("작가 : " + foundId.getAuthor());
                System.out.println("명언 : " + foundId.getContent());
            } else if (cmd.startsWith("삭제?")) {
                int id = Integer.parseInt(cmd.split("=")[1]);

                Motivation foundId = null;

                for (Motivation motivation : motivations) {
                    if (id == motivation.getId()) {
                        foundId = motivation;
                        break;
                    }
                }

                if (foundId == null) {
                    System.out.println(id + "번 명언이 존재하지 않습니다.");
                }

                motivations.remove(foundId);
                System.out.println(id + "번 명언이 삭제되었습니다.");
            }
        }

        sc.close();
    }
}

class Motivation {
    int id;
    String regDate;
    String content;
    String author;

    public Motivation(int id, String regDate, String content, String author) {
        this.id = id;
        this.regDate = regDate;
        this.content = content;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
