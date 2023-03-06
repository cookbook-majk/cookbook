package cookbook.cookbookrecipeapplication.models;

import java.io.Serializable;
import java.util.List;

public class SearchResults implements Serializable {
    public int offset;
    public int number;
    public List<Recipe> results;
    public long totalResults;


    public SearchResults() {
    }

    public SearchResults(int offset, int number, List<Recipe> results, long totalResults) {
        this.offset = offset;
        this.number = number;
        this.results = results;
        this.totalResults = totalResults;
    }


    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Recipe> getResults() {
        return results;
    }

    public void setResults(List<Recipe> results) {
        this.results = results;
    }

    public long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(long totalResults) {
        this.totalResults = totalResults;
    }
}
