import React from "react";
import CheckBoxGroup from "./CheckBoxGroup";
import CoordInput from "./CoordInput";
import {NavLink} from "react-router-dom";

export default class HitForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isCoordsXValid: false,
            isCoordYValid: false,
            isRadiusValid: false
        }
        this.handleFormSubmit = this.handleFormSubmit.bind(this)
        this.setCoordY = this.setCoordY.bind(this)
        this.setCoordsXIsValid = this.setCoordsXIsValid.bind(this)
        this.setCoordYIsValid = this.setCoordYIsValid.bind(this)
        this.setRadiusIsValid = this.setRadiusIsValid.bind(this)
        this.setCoordsX = this.setCoordsX.bind(this)
        this.setRadius = this.setRadius.bind(this)
        this.clearForm()
    }

    render() {
        return (
            <form className="container">
                <h2>Проверка вхождения в область</h2>
                <h4 className="form-label">Координата x</h4>
                <CheckBoxGroup availableValues={[-4, -3, -2, -1, 0, 1, 2, 3, 4]} setValuesInForm={this.setCoordsX}
                               prefix={'coordx'} setValidSt={this.setCoordsXIsValid}/>
                <CoordInput placeholderText={'-5...3'} labelText={'Координата y'}
                            setValueInForm={this.setCoordY} setValidSt={this.setCoordYIsValid}/>
                <h4 className="form-label">Радиус</h4>
                <CheckBoxGroup availableValues={[-4, -3, -2, -1, 0, 1, 2, 3, 4]} setValuesInForm={this.setRadius}
                               prefix={'radius'} setValidSt={this.setRadiusIsValid}/>
                <div className="btns-block">
                    <NavLink to="/logout" className="btn btn-block secondary">Выход</NavLink>
                    <button id="submitBtn" type="button" className="btn btn-block primary"
                            disabled={!(this.state.isCoordsXValid && this.state.isCoordYValid && this.state.isRadiusValid)}
                            onClick={this.handleFormSubmit}>Отправить</button>
                </div>
            </form>
        );
    }

    clearForm() {
        this.formData = {
            coordsX: [],
            coordY: '',
            radius: []
        }
    }

    setCoordsXIsValid(val) {
        this.setState({
            isCoordsXValid: val,
            isCoordYValid: this.state.isCoordYValid,
            isRadiusValid: this.state.isRadiusValid
        })
    }

    setCoordYIsValid(val) {
        this.setState({
            isCoordsXValid: this.state.isCoordsXValid,
            isCoordYValid: val,
            isRadiusValid: this.state.isRadiusValid
        })
    }

    setRadiusIsValid(val) {
        this.setState({
            isCoordsXValid: this.state.isCoordsXValid,
            isCoordYValid: this.state.isCoordYValid,
            isRadiusValid: val
        })
    }

    handleFormSubmit(evt) {
        this.props.addAction(this.formData)
    }

    setCoordY(value) {
        this.formData.coordY = value
    }

    setCoordsX(values) {
        this.formData.coordsX = values
    }

    setRadius(values) {
        this.formData.radius = values
        this.props.setSelectedRadius(values)
    }
}