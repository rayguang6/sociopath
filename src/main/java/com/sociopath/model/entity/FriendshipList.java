package com.sociopath.model.entity;



import java.util.ArrayList;


public class FriendshipList implements Comparable<FriendshipList> {
    ArrayList<Integer> relationship;
    
    public FriendshipList(ArrayList<Integer> i) {
        this.relationship=i;
    }
    
    public int getSize(){
        return relationship.size();
    }
    
    public String toString(){
        return relationship.toString();
    }
    
    public int get(int ind){
        return relationship.get(ind);
    }
    
    @Override
    public int compareTo(FriendshipList o) {
        //Sorting Way: The one with shortest distance in pathway will be prioritized, 
        //if the distance is same, sort according the first node from 1-end
        if(relationship.size()>o.getSize()) return 1;
        else if(relationship.size()<o.getSize()) return -1;
        else{
            for(int i=0; i<relationship.size(); i++){
                if(relationship.get(i)>o.get(i)){
                    return 1;
                }
                if(relationship.get(i)<o.get(i)){
                    return -1;
                }
            }
            return 0;
        }
    }
}
