import {makeScene2D, Txt} from '@motion-canvas/2d';
import {createRef, Direction, Origin, slideTransition, waitFor} from '@motion-canvas/core';
import {Caption, Text} from '../../common';

export default makeScene2D(function* (view) {
  const title = createRef<Txt>();

  const list = `- What are coroutines?
- How to start coroutine?
- What is structured concurrency?
- What are dispatchers?
- How to use flow?
- ...
- Questions 'What is it' or 'How to use it'`;

  view.add(
    <>
      <Caption ref={title} text={'Out of scope'} />
      <Text text={list} lineHeight={80} />
    </>
  );
  title().top(view.getOriginDelta(Origin.Top).addY(90));

  yield* slideTransition(Direction.Right);

  yield* waitFor(1);
});
