<?php
namespace models;

require_once 'db/DBHandler.php';
require_once 'db/StatementMaker.php';

use db\DBHandler;
use db\StatementMaker;

class CoordsModel
{
    protected $db;

    public function __construct() {
        $this->db = new DBHandler('coords');
    }

    public function getAll(): array {
        $stMaker = (new StatementMaker('coords'))->select();
        return  $this->db->fetch($stMaker);
    }

    public function add(...$params) {
        $stMaker = (new StatementMaker('coords'))->insertInto(
            ['coordinate_x', 'coordinate_y', 'radius', 'status', 'send_time'], prepare: true);
        $this->db->prepare($stMaker, ...$params);
    }

    public function __destruct()
    {
        $this->db = null;
    }
}