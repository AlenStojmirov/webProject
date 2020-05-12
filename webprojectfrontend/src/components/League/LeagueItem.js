import React, {Component} from "react";
import {Link} from "react-router-dom";
import PropTypes from "prop-types";
import {connect} from "react-redux";
import {deleteLeague} from "../../actions/leagueActions";

class LeagueItem extends Component {

    onDeleteClick = id =>{
        this.props.deleteLeague(id);
    };

    render() {
        const {league} = this.props;
        return (
            <tr>
                <td>{league.name}</td>
                <td>{league.sport.name}</td>
                <td>
                    <form>
                        <Link to={`/updateLeague/${league.id}`}>
                            <button className="btn btn-outline-warning m-1"><i className="fa fa-fw fa-edit"></i></button>
                        </Link>
                        <button onClick={this.onDeleteClick.bind(this,league.id)}
                                className="btn btn-outline-danger"><i className="fa fa-fw fa-trash"></i></button>
                    </form>
                </td>
            </tr>
        );
    }
}

LeagueItem.protoType={
    deleteLeague: PropTypes.func.isRequired
};

export default connect(null,{deleteLeague}) (LeagueItem);