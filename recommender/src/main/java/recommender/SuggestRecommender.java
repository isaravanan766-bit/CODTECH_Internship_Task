package recommender;

import java.io.File;
import java.util.List;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

public class SuggestRecommender {
    public static void main(String[] args) {
        try {
        	 DataModel model = new FileDataModel(new File("data.csv")); 
             ItemSimilarity similarity = new LogLikelihoodSimilarity(model);

             Recommender recommender = new GenericItemBasedRecommender(model, similarity);

             int userId = 2;
             List<RecommendedItem> recommendations = recommender.recommend(userId, 3);

             System.out.println("Recommended items for particular user " + userId + ":");
             if (recommendations.isEmpty()) {
                 System.out.println("No recommendations available to Show.");
             } else {
                 for (RecommendedItem item : recommendations) {
                     System.out.printf("Item %d (predicted rating detail: %.2f)\n", item.getItemID(), item.getValue());
                 }
             }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
