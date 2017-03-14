/* eslint-disable no-debugger */
/* eslint-disable no-unused-vars */
import { call } from 'redux-saga/effects';

export const changeInstanceName = name => ({
  type: 'CHANGE_INSTANCE_NAME',
  name,
});

export const changeStreamType = streamType => ({
  type: 'CHANGE_STREAM_TYPE',
  streamType,
});

export const addStreamToInstance = stream => ({
  type: 'ADD_STREAM_TO_INSTANCE',
  stream,
});

export const removeStreamFromInstance = stream => ({
  type: 'REMOVE_STREAM_FROM_INSTANCE',
  stream,
});

export const editInstance = instance => ({
  type: 'EDIT_INSTANCE',
  name: instance.name,
  streamType: instance.streamType,
  streams: instance.streams,
});
// export const saveInstance = (state) => {
//   // yield call()
//   // for each stream, add membership
//   // const promises = [];
//   // const streams = state.streams;
//   // const streamService = SYMPHONY.services.subscribe('stream-service');
//   // for (const stream in streams) {
//   //   if (streams[stream]) {
//   //     promises.push(streamService.addRoomMembership(streams[stream], botUserId));
//   //   }
//   // }
//   return {
//     type: 'SAVE_INSTANCE',
//     payload: streams,
//   };
// };
