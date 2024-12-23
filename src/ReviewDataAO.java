import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDataAO {

    public void addReview(int user_id, int product_id, int rating) throws SQLException {
        if(hasUserProduct(user_id,product_id)) {
            String query = "insert into reviews (product_id, user_id, rating) VALUES (?, ?, ?)";
            PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
            stmt.setInt(1, product_id);
            stmt.setInt(2, user_id);
            stmt.setInt(3, rating);
            int resultSet = stmt.executeUpdate();
        }
        else
            System.out.println("User doesn't have this product!");
    }

    public void deleteReview(int reviewId) throws SQLException {
        String query = "delete from reviews where review_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, reviewId);
        int resultSet = stmt.executeUpdate();
    }

    public Review getReviewById(int reviewId) throws SQLException {
        String query = "select * from reviews where review_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, reviewId);
        ResultSet resultSet = stmt.executeQuery();
        while(resultSet.next())
        {
            int review_id = resultSet.getInt("review_id");
            int user_id = resultSet.getInt("user_id");
            int product_id = resultSet.getInt("product_id");
            int rating = resultSet.getInt("rating");
            Review review = new Review(review_id, user_id, product_id, rating);
            return review;
        }
        return null;
    }


    public void updateReviewProductId(int reviewId, int newProductId) throws SQLException {
        String query = "Update reviews set product_id = ? where review_id = "+reviewId;
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1,newProductId);
        int resultSet = stmt.executeUpdate();
    }

    public void updateReviewRating(int reviewId, int newRating) throws SQLException {
        String query = "Update reviews set rating = ? where review_id = "+reviewId;
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1,newRating);
        int resultSet = stmt.executeUpdate();
    }

    public List<Review> getAllReviewsForProduct(int productId) throws SQLException {
        List<Review> reviews = new ArrayList<Review>();
        String query = "select * from reviews where product_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, productId);
        ResultSet resultSet = stmt.executeQuery();
        while(resultSet.next())
        {
            int review_id = resultSet.getInt("review_id");
            int user_id = resultSet.getInt("user_id");
            int rating = resultSet.getInt("rating");
            Review review = new Review(review_id, user_id, productId, rating);
            reviews.add(review);
        }
        return reviews;
    }

    public List<Review> getAllReviewsForUser(int userId) throws SQLException {
        List<Review> reviews = new ArrayList<Review>();
        String query = "select * from reviews where user_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, userId);
        ResultSet resultSet = stmt.executeQuery();
        while(resultSet.next())
        {
            int review_id = resultSet.getInt("review_id");
            int product_id = resultSet.getInt("product_id");
            int rating = resultSet.getInt("rating");
            Review review = new Review(review_id, userId, product_id, rating);
            reviews.add(review);
        }
        return reviews;
    }

    public void deleteAllReviewsForProduct(int productId) throws SQLException {
        String query = "delete from reviews where product_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, productId);
        int resultSet = stmt.executeUpdate();
    }

    public void deleteAllReviewsForUser(int userId) throws SQLException {
        String query = "delete from reviews where user_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, userId);
        int resultSet = stmt.executeUpdate();
    }


    public boolean hasUserProduct(int userId, int productId) throws SQLException {
        String query = "select * from transactions where user_id = ? and product_id = ?";
        PreparedStatement stmt = DBconnector.conn.prepareStatement(query);
        stmt.setInt(1, userId);
        stmt.setInt(2, productId);
        ResultSet resultSet = stmt.executeQuery();
        while(resultSet.next())
        {
            return true;
        }
        return false;
    }
}
