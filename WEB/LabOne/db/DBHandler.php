<?php

namespace db;

require_once 'Settings.php';
require_once 'db/StatementMaker.php';

use PDO;
use const Settings\DB_HOST, Settings\DB_PORT, Settings\DB_NAME,
    Settings\DB_USER, Settings\DB_PASSWORD;


class DBHandler {
    private $conn;

    public function __construct() {
        $this->conn = new PDO('pgsql:host=' . DB_HOST . ';port=' . DB_PORT . ';dbname=' . DB_NAME,
            DB_USER, DB_PASSWORD);
    }

    public function query(StatementMaker $statement) {
        return $this->conn->query($statement->getStatement(), );
    }

    public function prepare(StatementMaker $maker, ...$params) : bool {
        $qry = $this->conn->prepare($maker->getStatement());
        if (count($params)) {
            return $qry->execute($params);
        } else {
            return $qry->execute();
        }

    }

    public function fetch(StatementMaker $maker, ...$params) {
        $qry = $this->conn->prepare($maker->getStatement());
        $qry->execute($params);
        $arr = [];
        while ($row = $qry->fetch(PDO::FETCH_ASSOC)) {
            array_push($arr, $row);
        }
        return $arr;
    }
}
