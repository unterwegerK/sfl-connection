package de.ku.sfl.connection.objects;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/**
 * A reaction converts a series of educts into one product.
 */
public class Reaction {

    private final static String INTERNAL_PRODUCT_NAME = "internalProductName";
    private final static String PRODUCT_QUANTITY = "productQuantity";
    private final static String NUMBER_OF_EDUCTS = "numberOfEducts";
    private final static String INTERNAL_EDUCT_NAME = "internalEductName";
    private final static String EDUCT = "educt";

    private long id;
    private Substance product;
    private int productQuantity;

    private List<Substance> educts;

    /**
     * For serialization...
     */
    @SuppressWarnings("unused")
    private Reaction() {

    }

    public Reaction(Substance product, int productQuantity, List<Substance> educts) {
        this.product = product;
        this.productQuantity = productQuantity;
        this.educts = new Vector<>(educts);
    }

    public Reaction(JSONObject reactionObject, List<Substance> substances) throws JSONException {
        // Create table for fast lookup of substances
        HashMap<String, Substance> substanceTable = new HashMap<>();
        for (Substance substance : substances) {
            substanceTable.put(substance.getInternalName(), substance);
        }

        String internalProductName = getString(reactionObject, INTERNAL_PRODUCT_NAME);

        if (!substanceTable.containsKey(internalProductName)) {
            throw new RuntimeException("Substance " + internalProductName + " does not exist.");
        }
        product = substanceTable.get(internalProductName);
        productQuantity = reactionObject.getInt(PRODUCT_QUANTITY);

        int numberOfEducts = reactionObject.getInt(NUMBER_OF_EDUCTS);
        educts = new Vector<>(numberOfEducts);
        for (int i = 0; i < numberOfEducts; i++) {
            JSONObject eductObject = reactionObject.getJSONObject(EDUCT + i);
            String internalEductName = getString(eductObject, INTERNAL_EDUCT_NAME);

            if (!substanceTable.containsKey(internalEductName)) {
                throw new RuntimeException("Substance " + internalProductName + " does not exist.");
            }
            educts.add(substanceTable.get(internalEductName));
        }
    }

    public JSONObject serialize() throws JSONException {
        JSONObject variantObject = new JSONObject();
        variantObject.put(INTERNAL_PRODUCT_NAME, product.getInternalName());
        variantObject.put(PRODUCT_QUANTITY, getProductQuantity());

        List<Substance> educts = getEducts();
        variantObject.put(NUMBER_OF_EDUCTS, educts.size());

        for (int eductCounter = 0; eductCounter < educts.size(); eductCounter++) {
            Substance educt = educts.get(eductCounter);
            variantObject.put(EDUCT + eductCounter, educt);
        }
        return variantObject;
    }

    public static String getString(JSONObject jsonObject, String name) throws JSONException {
        if (jsonObject.isNull(name)) {
            return null;
        } else {
            return jsonObject.getString(name);
        }
    }

    public Substance getProduct() {
        return product;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public List<Substance> getEducts() {
        return new Vector<Substance>(educts);
    }
}
