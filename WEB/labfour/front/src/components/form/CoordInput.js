import React from "react";

export default class CoordInput extends React.Component {
    constructor(props) {
        super(props);
        this.state = {isValid: undefined}
        this.handleValueChange = this.handleValueChange.bind(this)
    }

    getClassName() {
        if (this.state.isValid === undefined) {
            return '';
        } else if (this.state.isValid) {
            return 'correct';
        } else {
            return 'wrong';
        }
    }

    render() {
        return (
            <div className="text-field-wrapper">
                <label htmlFor="textInput" className="form-label">{this.props.labelText}</label>
                <input className={this.getClassName()} name="coordinateY" id="textInput" type="text" placeholder={this.props.placeholderText} onInput={this.handleValueChange}/>
            </div>
        )
    }

    handleValueChange(evt) {
        let val = evt.target.value
        val = val.replace(',', '.')
        if (this.isValid(val)) {
            this.props.setValueInForm(val)
            this.props.setValidSt(true)
            this.setState({isValid: true})
        } else {
            this.props.setValidSt(false)
            this.setState({isValid: false})
        }
    }

    isValid(val) {
        return val.match(/^-?\d(\.\d{1,8})?$/g) && -5 <= val && val <= 3
    }
}