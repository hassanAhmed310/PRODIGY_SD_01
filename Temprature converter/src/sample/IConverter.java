package sample;

import java.util.List;

public interface IConverter<K, V> {
    public K convertTo(K from, V to);
    public List<K> convert(K value);
}
