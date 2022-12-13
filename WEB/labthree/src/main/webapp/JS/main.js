// FORM SETTINGS
const R_INPUT_ID = 'jsForm:rField';
const Y_INPUT_ID = 'jsForm:yField';
const X_INPUT_ID = 'jsForm:xField';


// PLOT SETTINGS
const MAX_X = 1200;
const MAX_Y = 1200;
const ZERO_X = MAX_X/2;
const ZERO_Y = MAX_Y/2;

document.getElementById(R_INPUT_ID).oninput = validateForm;
document.getElementById(Y_INPUT_ID).oninput = validateForm;

// Drawing
updatePlot();

function updatePlot() {
    let radius = 0;
    if (isRFieldValid()) radius = Number(getRField().value);
    drawFigure(radius);
    drawPoints();
}

function drawPoints() {
    let graphic = document.getElementById("plot"),
        pointsData = document.getElementById("pointsSet").innerHTML.split(/\s/),
        i = 0;

    while (i < pointsData.length - 2) {
        let x = Math.round(ZERO_X+Number(pointsData[i])*100),
            y = Math.round(ZERO_Y-Number(pointsData[i+1])*100);
        graphic.innerHTML += `<circle class="figure ${pointsData[i+2]}" cx="${x}" cy="${y}" r="10"/>`
        i += 3;
    }
}

function drawFigure(radius) {
    let graphic = document.getElementById("plot");
    radius = Math.round(radius*100);
    graphic.innerHTML = `
                <clipPath id="cut-off-bottom"><rect x="50%" y="50%" width="50%" height="50%"/></clipPath>
                <circle class="figure" cx="50%" cy="50%" r="${radius/2}" clip-path="url(#cut-off-bottom)"/>
                <polygon class="figure" points="600,600 600,${ZERO_Y - radius} ${ZERO_X + radius},600" />
                <rect class="figure" x="${ZERO_X-radius}" y="${ZERO_Y-radius/2}" width="${radius}" height="${radius/2}"/>
                <rect class="coordinate-line" x="0" y="597" width="100%" height="6"/>
                <rect class="coordinate-line" x="587" y="97" width="26" height="6"/>
                <rect class="coordinate-line" x="587" y="197" width="26" height="6"/>
                <rect class="coordinate-line" x="587" y="297" width="26" height="6"/>
                <rect class="coordinate-line" x="587" y="397" width="26" height="6"/>
                <rect class="coordinate-line" x="587" y="497" width="26" height="6"/>
                <rect class="coordinate-line" x="587" y="697" width="26" height="6"/>
                <rect class="coordinate-line" x="587" y="797" width="26" height="6"/>
                <rect class="coordinate-line" x="587" y="897" width="26" height="6"/>
                <rect class="coordinate-line" x="587" y="997" width="26" height="6"/>
                <rect class="coordinate-line" x="587" y="1097" width="26" height="6"/>
                <text class="coordinate-line" x="587" y="97" dx="-30" dy="15" font-size="40px">5</text>
                <text class="coordinate-line" x="587" y="197" dx="-30" dy="15" font-size="40px">4</text>
                <text class="coordinate-line" x="587" y="297" dx="-30" dy="15" font-size="40px">3</text>
                <text class="coordinate-line" x="587" y="397" dx="-30" dy="15" font-size="40px">2</text>
                <text class="coordinate-line" x="587" y="497" dx="-30" dy="15" font-size="40px">1</text>
                <text class="coordinate-line" x="587" y="697" dx="-35" dy="15" font-size="40px">-1</text>
                <text class="coordinate-line" x="587" y="797" dx="-35" dy="15" font-size="40px">-2</text>
                <text class="coordinate-line" x="587" y="897" dx="-35" dy="15" font-size="40px">-3</text>
                <text class="coordinate-line" x="587" y="997" dx="-35" dy="15" font-size="40px">-4</text>
                <text class="coordinate-line" x="587" y="1097" dx="-35" dy="15" font-size="40px">-5</text>

                <rect class="coordinate-line" x="597" y="0" width="6" height="100%"/>
                <rect class="coordinate-line" x="97" y="587" width="6" height="26"/>
                <rect class="coordinate-line" x="197" y="587" width="6" height="26"/>
                <rect class="coordinate-line" x="297" y="587" width="6" height="26"/>
                <rect class="coordinate-line" x="397" y="587" width="6" height="26"/>
                <rect class="coordinate-line" x="497" y="587" width="6" height="26"/>
                <rect class="coordinate-line" x="597" y="587" width="6" height="26"/>
                <rect class="coordinate-line" x="697" y="587" width="6" height="26"/>
                <rect class="coordinate-line" x="797" y="587" width="6" height="26"/>
                <rect class="coordinate-line" x="897" y="587" width="6" height="26"/>
                <rect class="coordinate-line" x="997" y="587" width="6" height="26"/>
                <rect class="coordinate-line" x="1097" y="587" width="6" height="26"/>
                <text class="coordinate-line" x="97" y="587" dx="-20" dy="60" font-size="40px">-5</text>
                <text class="coordinate-line" x="197" y="587" dx="-20" dy="60" font-size="40px">-4</text>
                <text class="coordinate-line" x="297" y="587" dx="-20" dy="60" font-size="40px">-3</text>
                <text class="coordinate-line" x="397" y="587" dx="-20" dy="60" font-size="40px">-2</text>
                <text class="coordinate-line" x="497" y="587" dx="-20" dy="60" font-size="40px">-1</text>
                <text class="coordinate-line" x="697" y="587" dx="-7" dy="60" font-size="40px">1</text>
                <text class="coordinate-line" x="797" y="587" dx="-7" dy="60" font-size="40px">2</text>
                <text class="coordinate-line" x="897" y="587" dx="-7" dy="60" font-size="40px">3</text>
                <text class="coordinate-line" x="997" y="587" dx="-7" dy="60" font-size="40px">4</text>
                <text class="coordinate-line" x="1097" y="587" dx="-7" dy="60" font-size="40px">5</text>
            `
}

// Form

function getXField() {
    return document.getElementById(X_INPUT_ID);}

function getYField() {
    return document.getElementById(Y_INPUT_ID);}

function getRField() {
    return document.getElementById(R_INPUT_ID);}

function setXValue(val) {
    getXField().value = val;
}

function setYValue(val) {
    getYField().value = val;
}

function setRValue(val) {
    getRField().value = val;
}

function isYFieldValid() {
    let yVal = getYField().value.replace(',', '.');
    setYValue(yVal);
    return yVal.match(/^-?\d(\.\d{1,8})?$/g) && -3 <= yVal && yVal <= 3
}

function isRFieldValid() {
    let rVal = getRField().value.replace(',', '.');
    setRValue(rVal);
    return rVal.match(/^-?\d(\.\d{1,8})?$/g) && 2 <= rVal && rVal <= 5;
}

function submitForm() {
    document.getElementById("jsForm:commandBlock").click();
}


document.getElementById("plot").onclick = function (e) {
    let target = this.getBoundingClientRect();
    let x = (e.clientX - target.left - target.width/2) / (target.width / 12),
        y = -(e.clientY - target.top - target.height/2) / (target.height / 12);

    setXValue(x.toFixed(4).toString());
    setYValue(y.toFixed(4).toString());

    if (isRFieldValid()) {
        submitForm();
    }
}

// validation

function validateForm() {
    let btn = document.getElementById('submitBtn'),
        yField = getYField(),
        rField = getRField();

    if (isYFieldValid() && isRFieldValid()) {
        yField.classList.remove("wrong");
        yField.classList.add("correct");
        rField.classList.remove("wrong");
        rField.classList.add("correct");
        btn.removeAttribute('disabled');
        updatePlot();
    } else {
        if (isRFieldValid()) {
            rField.classList.remove("wrong");
            rField.classList.add("correct");
            updatePlot();
        } else {
            rField.classList.remove("correct")
            rField.classList.add("wrong")
            btn.setAttribute('disabled', 'true');
        }
        if (isYFieldValid()) {
            yField.classList.remove("wrong");
            yField.classList.add("correct");
        } else {
            yField.classList.remove("correct");
            yField.classList.add("wrong");
            btn.setAttribute('disabled', 'true');
        }
    }
}

document.getElementById("jsForm").onsubmit = onSubmitFunction;

function onSubmitFunction() {
    document.getElementById('j_id1:jakarta.faces.ViewState:0').remove();
}

