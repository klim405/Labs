import React from "react";

function CheckBox(props) {
    let checkboxId = `checkbox-${props.prefix}${props.value}`
    return (
        <div>
            <input name={checkboxId} id={checkboxId} type="checkbox" value={props.value} onClick={props.onClick}/>
            <label htmlFor={checkboxId}>{props.value}</label>
        </div>
    );
}

export default class CheckBoxGroup extends React.Component {
    constructor(props) {
        super(props);
        this.values = []
        this.handleValueChange = this.handleValueChange.bind(this)
        this.state = {isValid: undefined}
    }

    getClassName() {
        if (this.state.isValid === undefined) {
            return 'checkbox-wrapper';
        } else if (this.state.isValid) {
            return 'checkbox-wrapper correct';
        } else {
            return 'checkbox-wrapper wrong';
        }
    }

    render() {
        return (
            <div className={this.getClassName()}>
                {this.props.availableValues.map((value) => (
                    <CheckBox prefix={this.props.prefix} value={value} onClick={this.handleValueChange} key={value}/>
                ))}
            </div>  
        );
    }


    handleValueChange(evt) {
        let val = +evt.target.value;
        if (evt.target.checked && !this.values.includes(val)) {
            this.values.push(val)
        } else {
            this.values = this.values.filter((e) => e !== val)
        }
        this.props.setValuesInForm(this.values)
        if (this.props.setValidSt !== undefined)
            this.props.setValidSt(this.values.length > 0)
        this.validate()
    }

    validate() {
        this.setState({isValid: this.values.length > 0})
    }
}