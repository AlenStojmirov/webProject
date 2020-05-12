import React, {Component} from "react";
import {Link} from "react-router-dom";
import PropTypes from "prop-types";
import {connect} from "react-redux";
import {deleteSport} from "../../actions/sportActions";

class SportItem extends Component {

    onDeleteClick = id =>{
        this.props.deleteSport(id);
    };

    render() {
        const {sport} = this.props;
        return (
            <tr>
                <td>{sport.name}</td>
                <td>
                    <form>
                        <Link to={`/updateSport/${sport.id}`}>
                            <button className="btn btn-outline-warning m-1"><i className="fa fa-fw fa-edit"></i></button>
                        </Link>
                        <button onClick={this.onDeleteClick.bind(this,sport.id)}
                                className="btn btn-outline-danger"><i className="fa fa-fw fa-trash"></i></button>
                    </form>
                </td>
            </tr>
        );
    }
}

SportItem.protoType={
    deleteSport: PropTypes.func.isRequired
};

export default connect(null,{deleteSport}) (SportItem);