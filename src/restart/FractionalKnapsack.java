package restart;

import java.util.Arrays;

public class FractionalKnapsack {

    //Function to get the maximum total value in the knapsack.
    double fractionalKnapsack(int W, Item arr[], int n) {
        Arrays.sort(arr, (a, b)->{
            double weight1 = (double)(a.value)/(double)(a.weight);
            double weight2 = (double)(b.value)/(double)(b.weight);
            if(weight1 == weight2){
                return 0;
            } else if (weight1>weight2){
                return -1;
            } else {
                return 1;
            }
        });
        double maxValue= 0;
        for(int i=0;i<n;i++){
            Item item = arr[i];
            if(W>=item.weight){
                W -= item.weight;
                maxValue += item.value;
                //System.out.println(W+","+maxValue+"::"+item.weight);
            } else {
                //System.out.println(W+"::"+item.weight);
                maxValue += (double)((double)W/(double)item.weight)*item.value;
                break;
            }
        }
        return maxValue;
    }

    public static void main(String[] args) {
        FractionalKnapsack fractionalKnapsack = new FractionalKnapsack();
        int n1=3;
        int W1 =50;
        Item[] arr1 = new Item[n1];
        arr1[0] = new Item(60,10);
        arr1[1] = new Item(100,20);
        arr1[2] = new Item(120,30);
        System.out.println(fractionalKnapsack.fractionalKnapsack(W1, arr1, n1));
        int n2=2;
        int W2 =50;
        Item[] arr2 = new Item[n2];
        arr2[0] = new Item(60,10);
        arr2[1] = new Item(100,20);
        System.out.println(fractionalKnapsack.fractionalKnapsack(W2, arr2, n2));
    }

}

class Item {
    int value, weight;
    Item(int value, int weight){
        this.value = value;
        this.weight = weight;
    }
}