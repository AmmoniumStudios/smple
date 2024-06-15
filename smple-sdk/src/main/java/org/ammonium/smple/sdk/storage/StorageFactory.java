package org.ammonium.smple.sdk.storage;

import org.ammonium.smple.sdk.storage.credentials.Credentials;

public interface StorageFactory<T> {

    void setup(Credentials credentials);

    T getConnection();

    void close();

}
