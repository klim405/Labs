import React from "react";
import Graph from "./graph/Graph";
import HitForm from "./form/HitForm";

export default class FormAndGraph extends React.Component {
    constructor(props) {
        super(props);
        this.state = {selectedRadius: []}
        this.setSelectedRadius = this.setSelectedRadius.bind(this)
    }
    render() {
        return (
            <div className="form-wrapper mt-75">
                <Graph hitInfos={this.props.hitInfos} selectedRadius={this.state.selectedRadius} addAction={this.props.addAction}/>
                <HitForm setSelectedRadius={this.setSelectedRadius} addAction={this.props.addAction}/>
            </div>
        )
    }

    setSelectedRadius(values) {
        this.setState({selectedRadius: values})
    }
}