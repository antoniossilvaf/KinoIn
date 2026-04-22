package com.kinoin;

import com.kinoin.enums.MovieStatus;
import com.kinoin.model.*;
import com.kinoin.service.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {

    static Scanner sc = new Scanner(System.in);

    static Catalog catalog = new Catalog();
    static Feed feed = new Feed();

    static User user = new User("João", "joao@email.com");

    static Recommendation recommendation;

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    public static void main(String[] args) {

        setup();

        int option;

        do {
            int alertCount = getAlertCount();
            clearScreen();
            System.out.println("==================================");
            System.out.println("         🎬 KINOIN APP");
            System.out.println("==================================");
            System.out.println("1. Catálogo");
            System.out.println("2. Comprar ingresso");
            System.out.println("3. Avaliar filme");
            System.out.println("4. Feed");
            System.out.println("5. Recomendações");
            System.out.println("6. Meu Perfil");
            System.out.println("7. Alertas (" + alertCount + ")");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1 -> { clearScreen(); catalogMenu(); waitEnter(); }
                case 2 -> { clearScreen(); buyTicket(); waitEnter(); }
                case 3 -> { clearScreen(); reviewMovie(); waitEnter(); }
                case 4 -> interactFeed();
                case 5 -> { clearScreen(); showRecommendations(); waitEnter(); }
                case 6 -> profileMenu();
                case 7 -> { clearScreen(); showAlerts(); waitEnter();
                }
            }

        } while (option != 0);
    }

    static void setup() {

        Movie m1 = new Movie("Batman", "Ação", 14, MovieStatus.NOW_SHOWING);
        Movie m2 = new Movie("Interestelar", "Ficção", 10, MovieStatus.COMING_SOON);
        Movie m3 = new Movie("Toy Story", "Animação", 0, MovieStatus.NOW_SHOWING);
        Movie m4 = new Movie("Vingadores", "Ação", 12, MovieStatus.NOW_SHOWING);
        Movie m5 = new Movie("Invocação do Mal", "Terror", 16, MovieStatus.NOW_SHOWING);
        Movie m6 = new Movie("Avatar 3", "Ficção", 12, MovieStatus.COMING_SOON);
        Movie m7 = new Movie("Homem-Aranha", "Ação", 12, MovieStatus.NOW_SHOWING);
        Movie m8 = new Movie("Frozen 3", "Animação", 0, MovieStatus.COMING_SOON);
        Movie m9 = new Movie("John Wick 4", "Ação", 16, MovieStatus.NOW_SHOWING);
        Movie m10 = new Movie("It: A Coisa 3", "Terror", 18, MovieStatus.COMING_SOON);

        catalog.addMovie(m1);
        catalog.addMovie(m2);
        catalog.addMovie(m3);
        catalog.addMovie(m4);
        catalog.addMovie(m5);
        catalog.addMovie(m6);
        catalog.addMovie(m7);
        catalog.addMovie(m8);
        catalog.addMovie(m9);
        catalog.addMovie(m10);

        Cinema cinema = new Cinema("CineMax", "Centro");

        cinema.addSession(new Session(m1, LocalDateTime.now().plusHours(1),1,20,generateRoom()));
        cinema.addSession(new Session(m1, LocalDateTime.now().plusHours(3),2,22,generateRoom()));
        cinema.addSession(new Session(m3, LocalDateTime.now().plusHours(2),3,15,generateRoom()));
        cinema.addSession(new Session(m4, LocalDateTime.now().plusHours(4),4,25,generateRoom()));
        cinema.addSession(new Session(m5, LocalDateTime.now().plusHours(5),5,30,generateRoom()));
        cinema.addSession(new Session(m7, LocalDateTime.now().plusHours(6),6,28,generateRoom()));
        cinema.addSession(new Session(m9, LocalDateTime.now().plusHours(7),7,35,generateRoom()));

        catalog.addCinema(cinema);

        User maria = new User("Maria", "maria@email.com");
        User carlos = new User("Carlos", "carlos@email.com");
        User ana = new User("Ana", "ana@email.com");
        User pedro = new User("Pedro", "pedro@email.com");

        user.addFriend(maria).accept();
        user.addFriend(carlos).accept();
        user.addFriend(ana).accept();
        user.addFriend(pedro).accept();

        maria.addToWishlist(m2);
        maria.addToWishlist(m6);
        maria.addToWishlist(m8);

        carlos.addToWishlist(m5);
        carlos.addToWishlist(m10);

        ana.addToWishlist(m8);
        ana.addToWishlist(m6);

        pedro.addToWishlist(m2);
        pedro.addToWishlist(m10);
        pedro.addToWishlist(m6);

        feed.addPost(new WatchedPost(maria, m1));
        feed.addPost(new ReviewPost(maria, new Review(maria, m1, 8.5, "Muito bom!")));

        feed.addPost(new WatchedPost(carlos, m4));
        feed.addPost(new ReviewPost(carlos, new Review(carlos, m4, 9.0, "Filme incrível!")));

        feed.addPost(new WatchedPost(ana, m3));
        feed.addPost(new ReviewPost(ana, new Review(ana, m3, 7.5, "Divertido!")));

        feed.addPost(new WatchedPost(pedro, m5));
        feed.addPost(new ReviewPost(pedro, new Review(pedro, m5, 8.0, "Assustador!")));

        recommendation = new Recommendation(catalog);
    }

    static boolean[][] generateRoom() {
        boolean[][] layout = new boolean[10][10];
        for (int i = 0; i < layout.length; i++)
            for (int j = 0; j < layout[i].length; j++)
                layout[i][j] = (i < 3); // VIP
        return layout;
    }

    static void catalogMenu() {

        System.out.println("1. Em cartaz");
        System.out.println("2. Em breve");

        int op = sc.nextInt(); sc.nextLine();

        List<Movie> movies = (op == 1)
                ? catalog.filterByStatus(MovieStatus.NOW_SHOWING)
                : catalog.filterByStatus(MovieStatus.COMING_SOON);

        if (movies.isEmpty()) {
            System.out.println("Nenhum filme.");
            return;
        }

        for (int i = 0; i < movies.size(); i++)
            System.out.println(i + " - " + movies.get(i).getTitle());

        System.out.print("Selecionar (-1 cancelar): ");
        int c = sc.nextInt(); sc.nextLine();

        if (c == -1 || c < 0 || c >= movies.size()) return;

        System.out.print("Adicionar à wishlist? (s/n): ");
        if (sc.nextLine().equalsIgnoreCase("s")) {
            user.addToWishlist(movies.get(c));
            System.out.println("Adicionado!");
        }
    }

    static void buyTicket() {

        while (true) {

            clearScreen();

            List<Movie> movies = catalog.getCinemas().stream()
                    .flatMap(c -> c.getSessions().stream())
                    .map(Session::getMovie)
                    .distinct()
                    .toList();

            if (movies.isEmpty()) {
                System.out.println("Nenhum filme disponível.");
                return;
            }

            System.out.println("=== ESCOLHA O FILME ===");
            System.out.println("(-1 para cancelar)\n");

            for (int i = 0; i < movies.size(); i++) {
                System.out.println(i + " - " + movies.get(i).getTitle());
            }

            int choice = sc.nextInt();

            if (choice == -1) return;

            if (choice < 0 || choice >= movies.size()) {
                System.out.println("Opção inválida.");
                waitEnter();
                continue;
            }

            Movie selected = movies.get(choice);

            List<Session> sessions = catalog.getCinemas().stream()
                    .flatMap(c -> c.getSessions().stream())
                    .filter(s -> s.getMovie().equals(selected))
                    .toList();

            if (sessions.isEmpty()) {
                System.out.println("❌ Sem sessões disponíveis.");
                waitEnter();
                continue;
            }

            clearScreen();

            System.out.println("=== SESSÕES DISPONÍVEIS ===");
            System.out.println("(-1 para voltar)\n");

            for (int i = 0; i < sessions.size(); i++) {
                Session s = sessions.get(i);
                System.out.println(i + " - Sala " + s.getRoomNumber()
                        + " | " + s.getDateTime().format(formatter)
                        + " | R$" + s.getBasePrice());
            }

            int sChoice = sc.nextInt();

            if (sChoice == -1) continue;

            if (sChoice < 0 || sChoice >= sessions.size()) {
                System.out.println("Sessão inválida.");
                waitEnter();
                continue;
            }

            Session session = sessions.get(sChoice);

            clearScreen();

            System.out.println("=== TIPO DE INGRESSO ===");
            System.out.println("1. Normal");
            System.out.println("2. Meia-entrada");
            System.out.println("(-1 cancelar)");

            int type = sc.nextInt();

            if (type == -1) continue;

            boolean isConcession = (type == 2);

            Seat[][] map = session.getSeatMap();

            while (true) {

                clearScreen();

                System.out.println("=== MAPA DA SALA ===");
                System.out.println("V = VIP | O = Livre | X = Ocupado\n");

                for (int i = 0; i < map.length; i++) {
                    for (int j = 0; j < map[i].length; j++) {

                        if (!map[i][j].isAvaliable()) {
                            System.out.print("X ");
                        } else if (map[i][j].isVipSeat()) {
                            System.out.print("V ");
                        } else {
                            System.out.print("O ");
                        }
                    }
                    System.out.println();
                }

                System.out.print("\nLinha (-1 cancelar): ");
                int row = sc.nextInt();
                if (row == -1) break;

                System.out.print("Coluna (-1 cancelar): ");
                int col = sc.nextInt();
                if (col == -1) break;

                if (row < 0 || row >= map.length || col < 0 || col >= map[0].length) {
                    System.out.println("Posição inválida.");
                    waitEnter();
                    continue;
                }

                if (!map[row][col].isAvaliable()) {
                    System.out.println("❌ Assento já está ocupado!");
                    waitEnter();
                    continue;
                }

                session.bookSeat(row, col);

                Ticket ticket;

                if (map[row][col].isVipSeat()) {
                    ticket = new VipTicket(user, session, row, col, 10);
                } else if (isConcession) {
                    ticket = new ConcessionTicket(user, session, row, col);
                } else {
                    ticket = new Ticket(user, session, row, col, session.getBasePrice());
                }

                user.addTicketToHistory(ticket);

                feed.addPost(new WatchedPost(user, selected));

                System.out.println("\n✅ Compra realizada com sucesso!");
                System.out.println(ticket);

                waitEnter();
                return;
            }
        }
    }

    static void showHistory() {

        clearScreen();

        System.out.println("=== HISTÓRICO DE COMPRAS ===\n");

        List<Ticket> history = user.getExpenseHistory();

        if (history.isEmpty()) {
            System.out.println("Nenhuma compra realizada.");
            return;
        }

        for (int i = 0; i < history.size(); i++) {

            Ticket t = history.get(i);

            Movie movie = t.getSession().getMovie();

            int row = t.getRow();
            int col = t.getColumn();

            // 🎯 REGRA DE TIPO
            String type;
            double price;

            if (t instanceof VipTicket) {
                type = "VIP ⭐";
                price = t.getSession().getBasePrice() + ((VipTicket) t).getVipSurcharge();
            }
            else if (t instanceof ConcessionTicket) {
                type = "MEIA 🎫";
                price = t.getSession().getBasePrice() / 2;
            }
            else {
                type = "NORMAL";
                price = t.getSession().getBasePrice();
            }

            char rowLetter = (char) ('A' + row);
            String seatLabel = rowLetter + "" + (col + 1);

            System.out.println("Compra #" + (i + 1));
            System.out.println("Filme: " + movie.getTitle());
            System.out.println("Sessão: "
                    + t.getSession().getDateTime().format(formatter));
            System.out.println("Assento: " + seatLabel);
            System.out.println("Tipo: " + type);
            System.out.println("Preço: R$" + String.format("%.2f", price));
            System.out.println("-----------------------------------");
        }
    }

    static void profileMenu() {

        while (true) {

            clearScreen();

            System.out.println("=== MEU PERFIL ===");
            System.out.println("1. Wishlist");
            System.out.println("2. Histórico");
            System.out.println("3. Amigos");
            System.out.println("0. Voltar");

            int op = sc.nextInt();
            sc.nextLine();

            if (op == 0) return;

            switch (op) {

                case 1 -> manageWishlist();

                case 2 -> {
                    showHistory();
                    waitEnter();
                }

                case 3 -> manageFriends();
            }
        }
    }

    static void manageWishlist() {

        while (true) {

            clearScreen();

            System.out.println("=== WISHLIST ===");
            System.out.println("1. Ver");
            System.out.println("2. Remover");
            System.out.println("0. Voltar");

            int op = sc.nextInt();
            sc.nextLine();

            if (op == 0) return;

            List<Movie> list = user.getWishlist();

            switch (op) {

                case 1 -> {
                    if (list.isEmpty()) {
                        System.out.println("Vazia.");
                    } else {
                        for (int i = 0; i < list.size(); i++) {
                            System.out.println(i + " - " + list.get(i).getTitle());
                        }
                    }
                    waitEnter();
                }

                case 2 -> {

                    if (list.isEmpty()) {
                        System.out.println("Nada pra remover.");
                        waitEnter();
                        continue;
                    }

                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i + " - " + list.get(i).getTitle());
                    }

                    int c = sc.nextInt();
                    sc.nextLine();

                    if (c >= 0 && c < list.size()) {
                        System.out.println("Removido: " + list.get(c).getTitle());
                        list.remove(c);
                    }

                    waitEnter();
                }
            }
        }
    }

    static void manageFriends() {

        while (true) {

            clearScreen();

            System.out.println("=== AMIGOS ===");
            System.out.println("1. Ver lista");
            System.out.println("2. Ver wishlist de amigo");
            System.out.println("0. Voltar");

            int op = sc.nextInt();
            sc.nextLine();

            if (op == 0) return;

            List<User> friends = user.getFriends();

            if (friends.isEmpty()) {
                System.out.println("Sem amigos.");
                waitEnter();
                return;
            }

            switch (op) {

                case 1 -> {
                    for (User f : friends) {
                        System.out.println(f.getName());
                    }
                    waitEnter();
                }

                case 2 -> {

                    for (int i = 0; i < friends.size(); i++) {
                        System.out.println(i + " - " + friends.get(i).getName());
                    }

                    int c = sc.nextInt();
                    sc.nextLine();

                    if (c >= 0 && c < friends.size()) {

                        User f = friends.get(c);

                        System.out.println("\nWishlist de " + f.getName());

                        if (f.getWishlist().isEmpty()) {
                            System.out.println("Vazia.");
                        } else {
                            f.getWishlist().forEach(m ->
                                    System.out.println("- " + m.getTitle()));
                        }
                    }

                    waitEnter();
                }
            }
        }
    }

    static void reviewMovie() {

        if (user.getExpenseHistory().isEmpty()) {
            System.out.println("Você ainda não assistiu nenhum filme.");
            return;
        }

        List<Movie> watchedMovies = user.getExpenseHistory().stream()
                .map(t -> t.getSession().getMovie())
                .distinct()
                .toList();

        List<Movie> reviewedMovies = feed.getAllPosts().stream()
                .filter(p -> p instanceof ReviewPost)
                .map(p -> ((ReviewPost) p).getReview())
                .filter(r -> r.getUser().equals(user))
                .map(Review::getMovie)
                .toList();

        List<Movie> toReview = watchedMovies.stream()
                .filter(m -> !reviewedMovies.contains(m))
                .toList();

        if (toReview.isEmpty()) {
            System.out.println("Você já avaliou todos os filmes assistidos.");
            return;
        }

        System.out.println("Escolha o filme para avaliar (-1 cancelar):");

        for (int i = 0; i < toReview.size(); i++) {
            System.out.println(i + " - " + toReview.get(i).getTitle());
        }

        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == -1 || choice < 0 || choice >= toReview.size()) return;

        Movie movie = toReview.get(choice);

        System.out.print("Nota (0-10): ");
        double score = sc.nextDouble();
        sc.nextLine();

        System.out.print("Comentário: ");
        String comment = sc.nextLine();

        Review review = new Review(user, movie, score, comment);

        feed.addPost(new ReviewPost(user, review));

        System.out.println("✅ Avaliação registrada com sucesso!");
    }

    static void interactFeed() {

        while (true) {

            clearScreen();

            List<Post> posts = feed.getFriendFeed(user);

            if (posts.isEmpty()) {
                System.out.println("Feed vazio.");
                waitEnter();
                return;
            }

            System.out.println("=== FEED ===");

            for (int i = 0; i < posts.size(); i++) {

                Post p = posts.get(i);

                String author = p.getAuthor().equals(user)
                        ? "(Você)"
                        : p.getAuthor().getName();

                String liked = p.isLikedBy(user) ? "❤️" : "🤍";

                System.out.println(i + " - " + author + " -> "
                        + p.getContent()
                        + " " + liked + " (" + p.getLikesCount() + ")");
            }

            System.out.println("\nEscolha um post para curtir/descurtir");
            System.out.println("ou -1 para voltar");

            int choice = sc.nextInt();

            if (choice == -1) return;

            if (choice < 0 || choice >= posts.size()) {
                System.out.println("Inválido.");
                waitEnter();
                continue;
            }

            boolean liked = posts.get(choice).toggleLike(user);

            System.out.println(liked ? "Você curtiu!" : "Você descurtiu!");

            waitEnter();
        }
    }

    static void showRecommendations() {

        clearScreen();

        System.out.println("=== RECOMENDAÇÕES ===");

        List<Review> myReviews = feed.getAllPosts().stream()
                .filter(p -> p instanceof ReviewPost)
                .map(p -> ((ReviewPost) p).getReview())
                .filter(r -> r.getUser().equals(user))
                .toList();

        if (myReviews.isEmpty()) {
            System.out.println("Avalie filmes para receber recomendações.");
            return;
        }

        Map<String, Integer> genreScore = new HashMap<>();

        for (Review r : myReviews) {
            if (r.getScore() >= 7) {
                String genre = r.getMovie().getGenre();
                genreScore.put(genre, genreScore.getOrDefault(genre, 0) + 1);
            }
        }

        if (genreScore.isEmpty()) {
            System.out.println("Nenhuma recomendação disponível.");
            return;
        }

        String favoriteGenre = Collections.max(
                genreScore.entrySet(),
                Map.Entry.comparingByValue()
        ).getKey();

        System.out.println("Baseado no seu gosto por: " + favoriteGenre + "\n");

        List<Movie> recommended = catalog.getAllMovies().stream()
                .filter(m -> m.getGenre().equalsIgnoreCase(favoriteGenre))
                .filter(m -> m.getMovieStatus() == MovieStatus.NOW_SHOWING
                        || m.getMovieStatus() == MovieStatus.COMING_SOON)
                .toList();

        if (recommended.isEmpty()) {
            System.out.println("Nenhum filme encontrado.");
            return;
        }

        recommended.forEach(m ->
                System.out.println("- " + m.getTitle()
                        + " (" + m.getMovieStatus() + ")"));
    }

    static int getAlertCount() {
        return (int) user.getWishlist().stream()
                .filter(m -> m.getMovieStatus() == MovieStatus.NOW_SHOWING)
                .count();
    }

    static void showAlerts() {

        System.out.println("=== ALERTAS ===");

        List<Movie> available = user.getWishlist().stream()
                .filter(m -> m.getMovieStatus() == MovieStatus.NOW_SHOWING)
                .toList();

        if (available.isEmpty()) {
            System.out.println("Nenhum alerta no momento.");
            return;
        }

        for (Movie m : available) {
            System.out.println("🎬 " + m.getTitle() + " já está em cartaz!");
        }

        System.out.println("\nDeseja remover algum da wishlist?");
        System.out.println("Digite o número ou -1 para sair:");

        for (int i = 0; i < available.size(); i++) {
            System.out.println(i + " - " + available.get(i).getTitle());
        }

        int choice = sc.nextInt();
        sc.nextLine();

        if (choice >= 0 && choice < available.size()) {
            Movie removed = available.get(choice);
            user.getWishlist().remove(removed);

            System.out.println("Removido da wishlist.");
        }
    }

    static void clearScreen() {
        for (int i = 0; i < 30; i++) System.out.println();
    }

    static void waitEnter() {
        System.out.println("\nENTER...");
        sc.nextLine();
    }
}