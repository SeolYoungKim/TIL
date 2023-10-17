package 북스터디.five_lines_of_code._4주차.pushpull;

public class PullArchitecture {
    record WebSite(String url) {
    }

    record User(String username) {
    }

    record BlogPost(User author, String id) {
    }

    static class BlogService {
        String generatePostLink(WebSite webSite, BlogPost blogPost) {
            String url = webSite.url();
            User author = blogPost.author();
            String username = author.username();
            String id = blogPost.id();

            return url + username + id;
        }
    }
}
