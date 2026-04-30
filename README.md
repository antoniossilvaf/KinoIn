# KinoIn

Sistema de cinema feito em Java com funcionalidades de catálogo, compra de ingressos, feed social, avaliações, wishlist e recomendações.

## Como executar

### Entre na pasta src e compile o projeto:

javac com/kinoin/Main.java

## Depois execute:

java com.kinoin.Main

## Requisitos
Java 17+
IDE Java (IntelliJ, Eclipse ou VSCode)

## Funcionalidades
Catálogo de filmes
Compra de ingressos
Seleção de assentos
Wishlist
Feed social
Avaliações
Histórico de compras
Recomendações personalizadas
Sistema de amigos
Alertas de lançamento

## Herança Abstrata e Polimorfismo

As classes ReviewPost e WatchedPost herdam da Classe abstrata Post, que possui o método abstrato getContent().

``` 
public abstract class Post {
    private String id;
    private User author;
    private LocalDateTime createdAt;
    private List<User> likes;

    public Post(User author) {
        this.id = "POST-" + UUID.randomUUID().toString().substring(0, 8);
        this.author = author;
        this.createdAt = LocalDateTime.now();
        this.likes = new ArrayList<>();
    }

    public abstract String getContent();
...

```
    public class ReviewPost extends Post {
        private Review review;

        public ReviewPost(User author, Review review) {
            super(author);
            this.review = review;
        }

        @Override
        public String getPostType() {
            return "REVIEW";
        }

        @Override
        public String getContent() {
            return String.format("Avaliou \"%s\" com %.1f/10\n\"%s\"",
                    review.getMovie().getTitle(),
                    review.getScore(),
                    review.getComment());
        }
    ...
```
    public class WatchedPost extends Post {
        private Movie movie;

        public WatchedPost(User author, Movie movie) {
            super(author);
            this.movie = movie;
        }

        @Override
        public String getPostType() { return "ASSISTIDO"; }

        @Override
        public String getContent() {
            return String.format("Acabou de assistir \"%s\"", movie.getTitle());
        }
...
```
Quando o feed vai ser gerado, uma lista de Posts (que podem ser tanto ReviewPost quanto WatchedPost) é iterada, e a função getContent é invocada para cada item dessa lista.

```
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
                        + p.getContent() // FUNÇÃO INVOCADA
                        + " " + liked + " (" + p.getLikesCount() + ")");
            }
...
```
