import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.io.IOException;
import java.util.List;

// Intern Name: Abhishek Choudhary
// Internship Task 4 :
// AI-based Recommendation System
// Task Description:
// Developed a recommendation engine using Java and Apache Mahout
// to suggest products or content based on user preferences.
// The program includes a working recommendation engine
// and sample data to demonstrate personalized recommendations.

class RecommendationSystem {
    public static void main(String[] args) {
        try {
            // Step 1: Load data from the CSV file
            DataModel model = new FileDataModel(new File("data.csv"));

            // Step 2: Compute similarity between users
            UserSimilarity similarity = new PearsonCorrelationSimilarity(model);

            // Step 3: Define neighborhood - users with similarity > 0.1
            UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);

            // Step 4: Build the recommender engine
            UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

            // Step 5: Recommend items for a given user (e.g., user ID 1)
            int userID = 1; // You can change this to test with other user IDs
            List<RecommendedItem> recommendations = recommender.recommend(userID, 3); // Recommend top 3 items

            // Step 6: Display recommendations
            System.out.println("Recommended items for user ID " + userID + ":");
            for (RecommendedItem item : recommendations) {
                System.out.println("Item ID: " + item.getItemID() + ", Estimated Preference: " + item.getValue());
            }

        } catch (IOException | TasteException e) {
            e.printStackTrace();
        }
    }
}
// To run this recommendation system:
// 1. Ensure you have Java and Apache Mahout libraries properly set up in your environment.
// 2. Compile the Java program using 'javac RecommendationSystem.java'.
// 3. Run the program using 'java RecommendationSystem' to see personalized product/content recommendations based on sample data
