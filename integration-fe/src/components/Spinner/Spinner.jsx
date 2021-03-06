import React, { PropTypes } from 'react';
import './styles/styles.less';

// Use named export for unconnected component (for tests)
export const Spinner = props => (
  <div className={props.loading ? 'spinner' : 'spinner spinner-opacity-0'}>
    <i className="fa fa-circle-o-notch fa-spin" />
    <p>Loading...</p>
  </div>
);

Spinner.propTypes = {
  loading: PropTypes.bool.isRequired,
};

export default Spinner;
