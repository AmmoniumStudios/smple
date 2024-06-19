package org.ammonium.smple.sdk.storage;

import org.ammonium.smple.sdk.storage.credentials.Credentials;

import java.util.Set;

public interface StorageFactory<T> {

    Set<String> getTables();

    void setup(Credentials credentials);

    T getConnection();

    void close();

}
