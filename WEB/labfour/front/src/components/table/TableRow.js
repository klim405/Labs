import React from "react";

export default class TableRow extends React.Component {
    render() {
        return (
            <div className="row">
                <div className="cell c15 mc15 sc20">{this.props.data.coordX}</div>
                <div className="cell c15 mc15 sc20">{this.props.data.coordY}</div>
                <div className="cell c15 mc15 sc20">{this.props.data.radius}</div>
                <div className="cell c15 mc20 sc40">{this.props.data.isHit ? 'HIT' : 'FAIL'}</div>
                <div className="cell c40 mc35 sc0">{this.props.data.time}</div>
            </div>
        );
    }
}