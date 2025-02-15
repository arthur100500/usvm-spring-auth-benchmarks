package org.usvm.spring.auth.benchmarks.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.usvm.spring.auth.benchmarks.models.PermissionType;
import org.usvm.spring.auth.benchmarks.models.Wallet;
import org.usvm.spring.auth.benchmarks.validation.PermissionAllowed;
import org.yaml.snakeyaml.events.Event;

public class WalletController {

    private void writeWalletToDb(Wallet w) {
        // Writes something to DB
        System.out.println(w.id);
    }
    
    private Wallet readWalletFromDb(String id) {
        // Get wallet by ID from DB
        var w = new Wallet();
        w.cash = 30;
        w.id = id;
        return w;
    }

    @PermissionAllowed(permissionAllowed = {PermissionType.CAN_READ})
    @PostMapping(value = "/read_wallet/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Wallet> readWallet(String id) {
        var wallet = readWalletFromDb(id);
        return new ResponseEntity<>(wallet, HttpStatus.OK);
    }

    @PermissionAllowed(permissionAllowed = {PermissionType.CAN_WRITE})
    @PostMapping(value = "/read_wallet/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Wallet> writeWallet(@RequestBody Wallet wallet) {
        writeWalletToDb(wallet);
        return new ResponseEntity<>(wallet, HttpStatus.OK);
    }
}
