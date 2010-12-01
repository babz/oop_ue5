
public interface RecursiveValueIter<Item, Value> extends RecursiveIter<Item>, ValueIter<Item, RecursiveIter<Item>, Value> {}