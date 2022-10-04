<?php

require_once 'db/DBHandler.php';
require_once 'db/StatementMaker.php';
require_once 'models/CoordsModel.php';

use db\DBHandler;
use db\StatementMaker;
use Models\CoordsModel;

//$db = new DBHandler();

//echo (new StatementMaker('coords'))->insertInto(
//    ['coordinate_x', 'coordinate_y', 'radius', 'status', 'send_time'], prepare: true);

//$m = new CoordsModel();
//$m->add(10, 9, 12, 'hit','1999-01-08 04:05:06');

//$res = $db->fetch((new StatementMaker('coords'))->select());
//foreach ($res as $row) {
//    foreach ($row as $index => $val) {
//        echo  $val . '    ';
//    }
//    echo "\n";
//}

echo time();



echo (int) 'ddd';

