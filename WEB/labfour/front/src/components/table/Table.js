import React from "react";
import TableRow from "./TableRow";

export default class Table extends React.Component {
    render() {
        return (
            <div className="table-wrapper">
                <div className="table">
                    <div className="row">
                        <div className="cell header c15 mc15 sc20">X</div>
                        <div className="cell header c15 mc15 sc20">Y</div>
                        <div className="cell header c15 mc15 sc20">R</div>
                        <div className="cell header c15 mc20 sc40">Status</div>
                        <div className="cell header c40 mc35 sc0">Time</div>
                    </div>
                    {this.props.data.map((hitInfo) => (
                        <TableRow data={hitInfo} key={hitInfo.id.toString()}/>
                    ))}
                </div>
            </div>
        );
    }
}