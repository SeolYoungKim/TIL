package 북스터디.five_lines_of_code._4주차.pushpull;

public class PushArchitecture {
    record WebSite(String url) {
        String generateLink(String username, String id) {
            return url + username + id;
        }
    }

    record User(String username) {
        String generateLink(WebSite website, String id) {
            return website.generateLink(username, id);
        }
    }

    record BlogPost(User author, String id) {
        String generateLink(WebSite webSite) {
            return author.generateLink(webSite, id);
        }
    }

    static class BlogService {
        String generatePostLink(WebSite webSite, BlogPost blogPost) {
            return blogPost.generateLink(webSite);
        }
    }
}
