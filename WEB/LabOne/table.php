<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>MySite</title>
    <style>
        .btn {
            display: inline-block;
            text-align: center;
            text-transform: uppercase;
            text-decoration: none;
            padding: 7px 20px;
            margin: 2px 10px;
            letter-spacing: 1px;
            font-size: 16px;
            background: transparent;
            border: 2px solid #31373C;
            border-radius: 25px;
            cursor: pointer;
        }

        .btn.secondary {
            color: #6F7680;
            border-color: #6F7680;
        }

        .btn.secondary:hover {
            color: #8C939D;
            border-color: #8C939D;
            box-shadow: 0 0 15px #6F7680;
            text-shadow: 0 0 5px #6F7680;
        }

        .btn.secondary:active {
            color: #f2f6fb;
            background-color: #8C939D;
            text-shadow: none;
        }

        *, *:before, *:after {
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }

        body {
            background: #000;
            min-height: 100vh;
        }

        main {
            display: flex;
            justify-content: center;
            min-height: 100vh;
        }

        .table {
            position: relative;
            width: 100%;
            height: 100%;
            max-width: 1000px;
            padding: 20px 5px;
            margin: 0 15px 15px;
            background-color: #161B21;
            border-radius: 25px;
        }

        .table:before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: linear-gradient(-45deg, #732B55, #82231F, #6C3115, #634012, #265228);
            filter: blur(20px);
            z-index: -1;
        }

        .row {
            display: flex;
            justify-content: center;
            align-items: baseline;
            border-bottom: 1px solid #000;
        }

        .row:last-child {
            border-bottom: none;
        }

        .cell {
            display: inline-block;
            padding: 8px 3px;
            color: #B1BAC4;
            font-size: 16px;
            text-align: center;
        }

        .cell.header {
            text-transform: uppercase;
            text-space: 2px;
            font-weight: bold;
        }

        .cell.c15 {
            width: 15%;
        }

        .cell.c20 {
            width: 20%;
        }

        .cell.c35 {
            width: 35%;
        }

        .cell.c40 {
            width: 40%;
        }

        @media (max-width: 992px) {
            .cell.mc15 {width: 15%;}
            .cell.mc20 {width: 20%;}
            .cell.mc35 {width: 35%;}
            .cell.mc40 {width: 40%;}
        }

        @media (max-width: 576px) {
            .cell.sc0 {display: none;}
            .cell.sc15 {width: 15%;}
            .cell.sc20 {width: 20%;}
            .cell.sc35 {width: 35%;}
            .cell.sc40 {width: 40%;}
        }

        .mt-75 {
            margin-top: 75px;
        }

        body {
            position: relative;
        }

        header {
            position: fixed;
            top: 0;
            left: 0;
            width: 100vw;
            height: 50px;
            padding: 6px 20px;
            font-size: 16px;
            color: #B1BAC4;
            background: #161B21;
            box-shadow: 0 0 25px #000;
            z-index: 100;
        }
    </style>
</head>
<body>
<body>
<header style="font-family: monospace">
    Климович Вадим, P32091,<br>№1009
</header>
<main>
    <div class="table mt-75">
        <div class="row">
            <div class="cell header c15 mc15 sc20">X</div>
            <div class="cell header c15 mc15 sc20">Y</div>
            <div class="cell header c15 mc15 sc20">R</div>
            <div class="cell header c15 mc20 sc40">Status</div>
            <div class="cell header c40 mc35 sc0">Time</div>
        </div>
        <?php
        require_once 'models/CoordsModel.php';
        use models\CoordsModel;
        $m =  new CoordsModel();
        foreach ($m->getAll() as $row) {
            echo <<<EOD
            <div class="row">
            <div class="cell c15 mc15 sc20">{$row['coordinate_x']}</div>
            <div class="cell c15 mc15 sc20">{$row['coordinate_y']}</div>
            <div class="cell c15 mc15 sc20">{$row['radius']}</div>
            <div class="cell c15 mc20 sc40">{$row['status']}</div>
            <div class="cell c40 mc35 sc0">{$row['send_time']}</div>
            </div>
            EOD;
        }
        ?>
        <div class="row">
            <a class="btn secondary" style="margin-top: 20px" href="form.php">Добавить</a>
        </div>

    </div>
</main>
</body>
</html>
