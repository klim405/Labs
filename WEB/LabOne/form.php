<?php
function checkValues(): bool {
    if (isset($_POST['coordinateX']) && isset($_POST['coordinateY']) && isset($_POST['radius'])) {
        $_POST['coordinateY'] = str_replace(',', '.', $_POST['coordinateY']);
        $checkX = in_array($_POST['coordinateX'], ['-2', '-1.5', '-1', '-0.5', '0', '0.5', '1', '1.5', '2']);
        $checkY = 0 <= (double) $_POST['coordinateY'] && (double) $_POST['coordinateY'] <= 3;
        $checkR = in_array($_POST['radius'], ['1', '1.5', '2', '2.5', '3']);
        return $checkX && $checkY && $checkR;
    }
    return false;
}

function checkHit(): bool {
    $x = (double) $_POST['coordinateX'];
    $y = (double) $_POST['coordinateY'];
    $r = (double) $_POST['radius'];

    if ($x >= 0 && $y >= 0) {
        return sqrt($x**2 + $y**2) <= $r/2;
    } elseif ($x >= 0  && $y <= 0) {
        return $x <= $r/2 && $y >= -$r;
    } elseif ($x <= 0  && $y <= 0) {
        $s1 = calcS(
                calcDistance(-$r/2,0, $x, $y),
                calcDistance($x, $y, 0, 0),
                abs($r/2)
            ) + calcS(
                calcDistance(0, 0, $x, $y),
                calcDistance($x, $y, 0, -$r),
                abs($r)
            )+ calcS(
                calcDistance(-$r/2,0, 0, -$r),
                calcDistance(0, -$r, $x, $y),
                calcDistance($x, $y, -$r/2,0),
            );
        $s2 = $r**2 / 4;
        return round($s2, 8) == round($s2, 8);
    } else {
        return false;
    }
}

function calcS($d1, $d2, $d3) {
    $p = ($d1 + $d2 + $d3) / 2;
    return sqrt($p*($p-$d1)*($p-$d2)*($p-$d3));
}

function calcDistance($x1, $y1, $x2, $y2) {
    return sqrt(($x1-$x2)**2 + ($y1-$y2)**2);
}
?>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>MySite</title>
    <style>
        *, *:before, *:after {
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }

        body {
            background-color: #000000;
        }

        h1, h2, h3, h4, h5, h6, p, a, label {
            color: #B1BAC4;
        }

        main {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: calc(100vh - 75px);
            margin-top: 75px;
        }

        @media (max-width: 576px) {
            main {
                display: block;
            }
        }

        @media (max-width: 992px) {
            main {
                flex-direction: column;
            }
        }

        .container {
            position: relative;
            background: #161B21;
            padding: 20px 20px 20px;
            margin: 40px;
            width: 400px;
            border-radius: 25px;
        }

        @media (max-width: 576px) {
            .container {
                width: 90%;
                margin: 30px 5%;
            }
        }

        .container:before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: linear-gradient(-45deg, #732B55, #82231F, #6C3115, #634012, #265228);
            /*background: linear-gradient(-45deg, #82231F, #6C3115, #634012, #265228, #1E4097, #503292, #732B55);*/
            filter: blur(20px);
            z-index: -1;
        }

        .form-label {
            display: block;
            margin: 25px 0 10px;
            padding-left: 15px;
            font-weight: normal;
        }

        .radio-wrapper {
            position: relative;
            display: flex;
            justify-content: center;
            align-items: baseline;
            border: #31373C solid 1px;
            border-radius: 15px;
            overflow: hidden;
        }

        .radio-wrapper input {
            position: absolute;
            top: 0;
            left: 0;
            visibility: hidden;
        }

        .radio-wrapper label {
            width: 100%;
            padding: 5px;
            text-align: center;
            cursor: pointer;
            color: #B1BAC4;
            border-right: #22262D 1px solid;
            transition: background-color .05s ease-out;
        }

        .radio-wrapper label:last-child {
            border: none;
        }

        .radio-wrapper label:hover {
            background: rgba(56, 109, 227, .5);
        }

        .radio-wrapper label:active {
            background: rgb(30, 64, 151, .7);
        }

        .radio-wrapper input:checked + label {
            background: rgba(56, 109, 227, .5);
        }

        .text-field-wrapper input[type="text"] {
            display: block;
            width: 100%;
            padding: 5px 15px;
            font-size: 16px;
            background: transparent;
            color: #B1BAC4;
            border: 1px solid #31373C;
            outline: none;
            border-radius: 15px;
        }

        .text-field-wrapper input[type="text"].wrong {
            border-color: #82231F;
            color: #EE8277;
        }

        .text-field-wrapper input[type="text"].correct {
            border-color: #275228;
            color: #76C96D;
        }

        .text-field-wrapper input[type="text"]:focus {
            border-color: #4A4F57;
            color: #f2f6fb;
        }

        .text-field-wrapper input[type="text"].wrong:focus {
            border-color: #A7322C;
            color: #F3A59B;
        }

        .text-field-wrapper input[type="text"].correct:focus {
            border-color: #356A35;
            color: #99E491;
        }

        /* ===== Оформление кнопок ===== */
        .btns-block {
            display: flex;
            margin: 35px 0 10px;
            justify-content: center;
        }

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

        .btn.btn-block {
            width: 100%;
        }

        .btn.primary {
            color: #2957C0;
            border-color: #2957C0;
        }

        .btn.primary:hover {
            color: #386DE3;
            border-color: #386DE3;
            box-shadow: 0 0 15px #2957C0;
            text-shadow: 0 0 5px #2957C0;
        }

        .btn.primary:active {
            color: #f2f6fb;
            background-color: #386DE3;
            text-shadow: none;
        }

        .btn.primary:disabled {
            color: #162C67;
            border-color: #162C67;
            background: transparent;
            box-shadow: none;
            text-shadow: none;
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



        /* ===== Оформление SVG ===== */

        .graph svg {
            width: 500px;
            height: 500px;
        }

        @media (max-width: 576px) {
            .graph svg {
                width: 90%;
                height: 90%;
                margin: 0 5%;
            }
        }

        .graph svg .figure,
        .graph svg .coordinate-line,
        .graph svg .coordinates {
            stroke-dasharray: none;
            stroke-linecap: butt;
            stroke-dashoffset: 0;
            stroke-linejoin: miter;
            stroke-miterlimit: 4;
            fill-rule: nonzero;
            opacity: 1;
        }

        .graph svg .figure {
            fill: #172C67;
            stroke-width: 0;
        }

        .graph svg .coordinate-line {
            stroke: #8C939D;
            stroke-width: 2;
            fill-opacity: 0;
        }

        .graph svg .coordinates {
            fill: #8C939D;
            stroke: none;
            stroke-width: 1;
            white-space: pre;
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
<header style="font-family: monospace">
    Климович Вадим, P32091,<br>№1009
</header>
<main class="mt-75">
    <div class="graph">
        <svg viewBox="0 0 400 400" xml:space="preserve">
            <g transform="matrix(1 0 0 1 200 240)" id="BOi6lwT9HGua5aSPv8gxG">
                <path class="figure"
                      vector-effect="non-scaling-stroke" transform=" translate(-80, -120)"
                      d="M 80 0 C 160.12821 0 160 80 160 80 L 160 240 L 80 240 L 0 80 L 80 80 z"
                      stroke-linecap="round"/>
            </g>
            <g transform="matrix(1 0 0 1 199.5 199.5)" id="izm8TwvhK_4yLgvcgul0m">
                <path class="coordinate-line" vector-effect="non-scaling-stroke"
                      d="M -200 0 L 200 0"
                      stroke-linecap="round"/>
            </g>
            <g transform="matrix(1 0 0 1 200.5 200.5)" id="WkVDkCTJikb5bSSxosQh9">
                <path class="coordinate-line" vector-effect="non-scaling-stroke"
                      d="M 0 -200 L 0 200"
                      stroke-linecap="round"/>
            </g>
            <g transform="matrix(-1 0 0 1 203.81 10.5)" id="iHoyWPlwfxTSgLfinwvGn">
                <path class="coordinate-line" vector-effect="non-scaling-stroke"
                      d="M 3.27271 -10 C 3.89191 9.504639999999998 -3.3104699999999996 10 -3.3104699999999996 10"
                      stroke-linecap="round"/>
            </g>
            <g transform="matrix(1 0 0 1 197.09 10.71)" id="oXHxoJOXi8ycLWuqnvhvn">
                <path class="coordinate-line" vector-effect="non-scaling-stroke"
                      d="M 3.27271 -10 C 3.89191 9.504639999999998 -3.3104699999999996 10 -3.3104699999999996 10"
                      stroke-linecap="round"/>
            </g>
            <g transform="matrix(1 0 0 1 389.23 196.13)" id="lTN1Ca4bRjMqcfxYk_3Q5">
                <path class="coordinate-line" vector-effect="non-scaling-stroke" transform=" translate(-9.77, -3.5)"
                      d="M 19.53812 7 C 0.04193000000000069 6.8595 0 0 0 0" stroke-linecap="round"/>
            </g>
            <g transform="matrix(1 0 0 1 200 119.5)" id="RH3GV0GvvqODrF_reARmP">
                <path class="coordinate-line" vector-effect="non-scaling-stroke"  d="M -10 0 L 10 0"
                      stroke-linecap="round"/>
            </g>
            <g transform="matrix(1 0 0 1 200 40.5)" id="97tOfo00x5teGLS6EIGYb">
                <path class="coordinate-line" vector-effect="non-scaling-stroke"  d="M -10 0 L 10 0"
                      stroke-linecap="round"/>
            </g>
            <g transform="matrix(1 0 0 1 200 359.5)" id="5Zhjuec97z705hgaKfzmb">
                <path class="coordinate-line" vector-effect="non-scaling-stroke"  d="M -10 0 L 10 0"
                      stroke-linecap="round"/>
            </g>
            <g transform="matrix(1 0 0 1 200 280.5)" id="tGHx3bZ77MW2MLLmgNoa7">
                <path class="coordinate-line" vector-effect="non-scaling-stroke"  d="M -10 0 L 10 0"
                      stroke-linecap="round"/>
            </g>
            <g transform="matrix(1 0 0 1 120 198.93)" id="bi-c7FJo3labj5qNevCPq">
                <path class="coordinate-line" vector-effect="non-scaling-stroke"  d="M 0 -10 L 0 10"
                      stroke-linecap="round"/>
            </g>
            <g transform="matrix(1 0 0 1 40.5 199.06)" id="QqeypzyA8yzaeNxQCurfk">
                <path class="coordinate-line" vector-effect="non-scaling-stroke"  d="M 0 -10 L 0 10"
                      stroke-linecap="round"/>
            </g>
            <g transform="matrix(1 0 0 1 359.5 198.86)" id="iAF50ycFA3SuJNSxDLB24">
                <path class="coordinate-line" vector-effect="non-scaling-stroke"  d="M 0 -10 L 0 10"
                      stroke-linecap="round"/>
            </g>
            <g transform="matrix(1 0 0 1 280 199)" id="DWMlAgUVzfEnRiwiqv0BY">
                <path class="coordinate-line" vector-effect="non-scaling-stroke"  d="M 0 -10 L 0 10"
                      stroke-linecap="round"/>
            </g>
            <g transform="matrix(1 0 0 1 44.51 187.51)"  id="D4-4o2DL5IMMQXasF5xau">
                <text xml:space="preserve" font-family="Open Sans" font-size="13" font-style="normal" font-weight="700"
                      letter-spacing="0.026" line-height="1" class="coordinates"><tspan x="-10.99" y="0.44" >-</tspan>
                    <tspan x="-6.78" y="0.44">R</tspan></text>
            </g>
            <g transform="matrix(1 0 0 1 119 187.51)"  id="zIJ_yBPlU6gO7cLxklQog">
                <text xml:space="preserve" font-family="Open Sans" font-size="13" font-style="normal" font-weight="700"
                      letter-spacing="0.026" line-height="1" class="coordinates"><tspan x="-12.82" y="0.44" >-</tspan>
                    <tspan x="-8.61" y="0.44">R</tspan>
                    <tspan x="-0.01" y="0.44">/</tspan>
                    <tspan x="5.39" y="0.44">2</tspan></text>
            </g>
            <g transform="matrix(1 0 0 1 281.74 187.51)"  id="FpHfiuyFZMRk7fPj35xr_">
                <text xml:space="preserve" font-family="Open Sans" font-size="13" font-style="normal" font-weight="700"
                      letter-spacing="0.026" line-height="1" class="coordinates"><tspan x="-10.99" y="0.44" >R</tspan>
                    <tspan x="-2.38" y="0.44">/</tspan>
                    <tspan x="3.01" y="0.44">2</tspan></text>
            </g>
            <g transform="matrix(1 0 0 1 366.51 187.51)"  id="gi6ozxH9hLh42r9C6i3Gw">
                <text xml:space="preserve" font-family="Open Sans" font-size="13" font-style="normal" font-weight="700"
                      letter-spacing="0.026" line-height="1" class="coordinates"><tspan x="-10.99" y="0.44" >R</tspan></text>
            </g>
            <g transform="matrix(1 0 0 1 191.49 42.95)"  id="80e9-pfSUIta-yE6NqyUu">
                <text xml:space="preserve" font-family="Open Sans" font-size="14" font-style="normal" font-weight="700"
                      letter-spacing="0.028" line-height="1" class="coordinates"><tspan x="-10.99" y="1.32" >R</tspan></text>
            </g>
            <g transform="matrix(1 0 0 1 180 123.23)"  id="9A5PyZe4HZuU1VXevr4zD">
                <text xml:space="preserve" font-family="Open Sans" font-size="13" font-style="normal" font-weight="700"
                      letter-spacing="0.026" line-height="1" class="coordinates"><tspan x="-10.99" y="0.44" >R</tspan>
                    <tspan x="-2.38" y="0.44">/</tspan>
                    <tspan x="3.01" y="0.44">2</tspan></text>
            </g>
            <g transform="matrix(1 0 0 1 176.08 285.02)"  id="dWQJ1hzSCzUCXWOkkMW4W">
                <text xml:space="preserve" font-family="Open Sans" font-size="13" font-style="normal" font-weight="700"
                      letter-spacing="0.026" line-height="1" class="coordinates"><tspan x="-12.82" y="0.44" >-</tspan>
                    <tspan x="-8.61" y="0.44">R</tspan>
                    <tspan x="-0.01" y="0.44">/</tspan>
                    <tspan x="5.39" y="0.44">2</tspan></text>
            </g>
            <g transform="matrix(1 0 0 1 188.51 362.98)"  id="zX1PA4UH3C26MtRsDNC-d">
                <text xml:space="preserve" font-family="Open Sans" font-size="13" font-style="normal" font-weight="700"
                      letter-spacing="0.026" line-height="1" class="coordinates"><tspan x="-10.99" y="0.44" >-</tspan>
                    <tspan x="-6.78" y="0.44">R</tspan></text>
            </g>
            <g transform="matrix(1 0 0 -1 389.23 203.22)" id="56GuZsHWJj6WkwKfGNJI2">
                <path class="coordinate-line" vector-effect="non-scaling-stroke" transform=" translate(-9.77, -3.5)"
                      d="M 19.53812 7 C 0.04193000000000069 6.8595 0 0 0 0" stroke-linecap="round"/>
            </g>
        </svg>
    </div>
    <form class="container" method="POST">
        <?php
        require_once 'models/CoordsModel.php';
        use models\CoordsModel;
        if (checkValues()) {
            $m = new CoordsModel();
            $m->add($_POST['coordinateX'], $_POST['coordinateY'], $_POST['radius'],
            checkHit() ? 'Попал' : ' Промазал', (new DateTime())->format('Y-m-d H:i:s'));
        }
        ?>
        <h2>Проверка вхождения в область</h2>
        <h4 class="form-label">Координата x</h4>
        <div class="radio-wrapper">
            <input name="coordinateX" id="radio1" type="radio" value="-2" required >
            <label for="radio1">-2</label>
            <input name="coordinateX" id="radio2" type="radio" value="-1.5" required>
            <label for="radio2">-1.5</label>
            <input name="coordinateX" id="radio3" type="radio" value="-1" required>
            <label for="radio3">-1</label>
            <input name="coordinateX" id="radio4" type="radio" value="-0.5" required>
            <label for="radio4">-0.5</label>
            <input name="coordinateX" id="radio5" type="radio" value="0" required checked>
            <label for="radio5">0</label>
            <input name="coordinateX" id="radio6" type="radio" value="0.5" required>
            <label for="radio6">0.5</label>
            <input name="coordinateX" id="radio7" type="radio" value="1" required>
            <label for="radio7">1</label>
            <input name="coordinateX" id="radio8" type="radio" value="1.5" required>
            <label for="radio8">1.5</label>
            <input name="coordinateX" id="radio9" type="radio" value="2" required>
            <label for="radio9">2</label>
        </div>
        <div class="text-field-wrapper">
            <label for="textInput" class="form-label">Координата y</label>
            <input name="coordinateY" id="textInput" type="text" placeholder="0...3"  onchange="validateForm()">
        </div>
        <h4 class="form-label">Радиус</h4>
        <div class="radio-wrapper">
            <input name="radius" id="radio10" type="radio" value="1" required>
            <label for="radio10">1</label>
            <input name="radius" id="radio11" type="radio" value="1.5" required>
            <label for="radio11">1.5</label>
            <input name="radius" id="radio12" type="radio" value="2" required checked>
            <label for="radio12">2</label>
            <input name="radius" id="radio13" type="radio" value="2.5" required>
            <label for="radio13">2.5</label>
            <input name="radius" id="radio14" type="radio" value="3" required>
            <label for="radio14">3</label>
        </div>
        <div class="btns-block">
            <a class="btn btn-block secondary" href="table.php">Таблица</a>
            <button id="submitBtn" class="btn btn-block primary" disabled>Отправить</button>
        </div>
    </form>

    <script type="text/javascript">
        let textInput = document.getElementById('textInput');
        textInput.oninput = validateForm;

        function validateForm() {
            let btn = document.getElementById('submitBtn'),
                textInput = document.getElementById('textInput'),
                inputVal = textInput.value;

            inputVal = inputVal.replace(',', '.');
            if (inputVal.match(/^\d(\.\d{1,8})?$/g) && 0 <= inputVal && inputVal <= 3) {
                textInput.classList.remove("wrong")
                textInput.classList.add("correct")
                btn.removeAttribute('disabled');
            } else {
                textInput.classList.remove("correct")
                textInput.classList.add("wrong")
                btn.setAttribute('disabled', 'true');
            }
        }

    </script>
</main>
</body>
</html>